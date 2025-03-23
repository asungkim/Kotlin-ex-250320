package domain.wiseSaying

import global.Request

class WiseSayingController {
    private val wiseSayingService = WiseSayingService()

    fun write() {
        print("명언 : ")
        val saying = readlnOrNull() ?: ""
        print("작가 : ")
        val author = readlnOrNull() ?: ""

        wiseSayingService.write(saying, author)
    }

    fun print() {
        println("번호 / 작가 / 명언")
        println("----------------------")

        val wiseSayingList = wiseSayingService.getItems()

        wiseSayingList.reversed().forEach {
            println("${it.id} / ${it.author} / ${it.saying}")
        }
    }

    fun delete(rq: Request) {
        val id = rq.getParam("id")?.toIntOrNull()

        if (id == null) {
            println("id는 정수입니다. 다시 입력해주세요.")
            return
        }

        val wiseSaying = wiseSayingService.getItem(id)
        wiseSaying?.let {
            wiseSayingService.delete(it)
            println("${id}번 명언을 삭제했습니다.")
        } ?: println("${id}번 명언은 존재하지 않습니다.")
    }

    fun modify(rq: Request) {
        val id = rq.getParam("id")?.toIntOrNull()

        if (id == null) {
            println("id는 정수입니다. 다시 입력해주세요.")
            return
        }

        val wiseSaying = wiseSayingService.getItem(id)
        wiseSaying?.let {
            println("명언(기존) : ${wiseSaying.saying}")
            print("명언: ")
            val saying = readlnOrNull() ?: ""

            println("작가(기존) : ${wiseSaying.author}")
            print("작가: ")
            val author = readlnOrNull() ?: ""

            wiseSayingService.modify(wiseSaying, saying, author)
            println("${id}번 명언을 수정했습니다.")
        } ?: println("${id}번 명언은 존재하지 않습니다.")
    }


}