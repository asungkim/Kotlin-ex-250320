package domain.wiseSaying.repository

import domain.wiseSaying.entity.WiseSaying

class WiseSayingMemRepository: WiseSayingRepository {
    private val wiseSayingList = mutableListOf<WiseSaying>()
    private var lastId: Int = 0

    override fun save(wiseSaying: WiseSaying): WiseSaying {

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

    override fun findAll(): MutableList<WiseSaying> {
        return wiseSayingList
    }

    override fun findById(id: Int): WiseSaying? {
        return wiseSayingList.find { it.id == id }
    }

    override fun delete(wiseSaying: WiseSaying) {
        wiseSayingList.remove(wiseSaying)
    }

    override fun clear() {
        wiseSayingList.clear()
        lastId = 0
    }
}