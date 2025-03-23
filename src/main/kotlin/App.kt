import domain.system.SystemController
import domain.wiseSaying.WiseSayingController
import global.Request

class App {
    fun run() {

        val systemController = SystemController()
        val wiseSayingController = WiseSayingController()

        println("== 명언 앱 ==")
        while (true) {
            print("명령) ")
            val input = readlnOrNull() ?: ""

            val rq = Request(input)

            when (rq.actionName) {
                "등록" -> wiseSayingController.write()
                "목록" -> wiseSayingController.print()
                "수정" -> wiseSayingController.modify(rq)
                "삭제" -> wiseSayingController.delete(rq)
                "종료" -> {
                    systemController.exit()
                    break
                }

                else -> {
                    println("잘못된 명령어 입니다. 다시 시도해주세요.")
                }
            }
        }
    }
}