package domain.wiseSaying.service

import domain.wiseSaying.entity.WiseSaying
import global.SingletonScope

class WiseSayingService {
    private val wiseSayingRepository = SingletonScope.wiseSayingFileRepository

    fun write(saying: String, author: String): WiseSaying {
        val wiseSaying = WiseSaying(content = saying, author = author)
        return wiseSayingRepository.save(wiseSaying)
    }

    fun getItems(keywordType: String, keyword: String, page: Int, pageSize: Int): List<WiseSaying> {
        val wiseSayings = wiseSayingRepository.findAll()

        val filteredList = when (keywordType) {
            "content" -> wiseSayings.filter { it.content.contains(keyword) }
            "author" -> wiseSayings.filter { it.author.contains(keyword) }
            else -> wiseSayings
        }

        val startIdx = (page - 1) * pageSize
        return filteredList.drop(startIdx).take(pageSize)
    }

    fun getItem(id: Int): WiseSaying? {
        return wiseSayingRepository.findById(id)
    }

    fun delete(wiseSaying: WiseSaying) {
        return wiseSayingRepository.delete(wiseSaying)
    }

    fun modify(wiseSaying: WiseSaying, saying: String, author: String): WiseSaying {
        return wiseSayingRepository.save(wiseSaying.copy(content = saying, author = author))
    }

    fun build() {
        wiseSayingRepository.build()
    }
}