package domain.wiseSaying.controller

import global.Request
import global.SingletonScope

class WiseSayingController {
    private val wiseSayingService = SingletonScope.wiseSayingService

    fun write() {
        print("명언 : ")
        val saying = readlnOrNull() ?: ""
        print("작가 : ")
        val author = readlnOrNull() ?: ""

        val wiseSaying = wiseSayingService.write(saying, author)
        println("${wiseSaying.id}번 명언이 등록되었습니다.")
    }

    fun print(rq: Request) {
        val keyword = rq.paramMap["keyword"] ?: ""
        val keywordType = rq.paramMap["keywordType"] ?: ""
        val page: Int = rq.paramMap["page"]?.toInt() ?: 1
        val pageSize: Int = rq.paramMap["pageSize"]?.toInt() ?: 5

        if (keyword != "" && keywordType != "") {
            println("----------------------")
            println("검색타입 : $keywordType")
            println("검색어 : $keyword")
            println("----------------------")
        }

        println("번호 / 작가 / 명언")
        println("----------------------")

        val wiseSayingList = wiseSayingService.getItems(keywordType, keyword, page, pageSize)
        wiseSayingList.reversed().forEach {
            println("${it.id} / ${it.author} / ${it.content}")
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
            println("명언(기존) : ${wiseSaying.content}")
            print("명언: ")
            val saying = readlnOrNull() ?: ""

            println("작가(기존) : ${wiseSaying.author}")
            print("작가: ")
            val author = readlnOrNull() ?: ""

            wiseSayingService.modify(wiseSaying, saying, author)
            println("${id}번 명언을 수정했습니다.")
        } ?: println("${id}번 명언은 존재하지 않습니다.")
    }

    fun build() {
        wiseSayingService.build()
        println("data.json 파일의 내용이 갱신되었습니다.")
    }


}