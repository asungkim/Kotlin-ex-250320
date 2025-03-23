package domain.wiseSaying.service

import domain.wiseSaying.entity.WiseSaying
import global.SingletonScope

class WiseSayingService {
    private val wiseSayingRepository = SingletonScope.wiseSayingFileRepository

    fun write(saying: String, author: String): WiseSaying {
        val wiseSaying = WiseSaying(content = saying, author = author)
        return wiseSayingRepository.save(wiseSaying)
    }

    fun getItems(): List<WiseSaying> {
        return wiseSayingRepository.findAll()
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
}