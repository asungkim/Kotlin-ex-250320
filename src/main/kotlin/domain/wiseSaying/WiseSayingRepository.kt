package domain.wiseSaying

class WiseSayingRepository {
    private val wiseSayingList = mutableListOf<WiseSaying>()
    private var lastId: Int = 0

    fun save(wiseSaying: WiseSaying): WiseSaying {

        if (wiseSaying.isNew()) {
            val new = wiseSaying.copy(id = ++lastId)
            wiseSayingList.add(new)
            return new
        }

        wiseSayingList.indexOfFirst { it.id == wiseSaying.id }.let {
            wiseSayingList[it] = wiseSaying
        }

        return wiseSaying
    }

    fun findAll(): MutableList<WiseSaying> {
        return wiseSayingList
    }

    fun findById(id: Int): WiseSaying? {
        return wiseSayingList.find { it.id == id }
    }

    fun delete(wiseSaying: WiseSaying) {
        wiseSayingList.remove(wiseSaying)
    }
}