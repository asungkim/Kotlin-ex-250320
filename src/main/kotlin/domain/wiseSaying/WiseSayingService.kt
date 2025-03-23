package domain.wiseSaying

class WiseSayingService {
    private val wiseSayingRepository = WiseSayingRepository()

    fun write(saying: String, author: String): WiseSaying {
        val wiseSaying = WiseSaying(saying = saying, author = author)
        return wiseSayingRepository.save(wiseSaying)
    }

    fun getItems(): MutableList<WiseSaying> {
        return wiseSayingRepository.findAll()
    }

    fun getItem(id: Int): WiseSaying? {
        return wiseSayingRepository.findById(id)
    }

    fun delete(wiseSaying: WiseSaying) {
        return wiseSayingRepository.delete(wiseSaying)
    }

    fun modify(wiseSaying: WiseSaying, saying: String, author: String): WiseSaying {
        return wiseSayingRepository.save(wiseSaying.copy(saying = saying, author = author))
    }
}