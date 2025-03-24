import global.Request
import global.SingletonScope

class App {
    fun run() {

        val systemController = SingletonScope.systemController
        val wiseSayingController = SingletonScope.wiseSayingController

        println("== 명언 앱 ==")
        while (true) {
            print("명령) ")
            val input = readlnOrNull() ?: ""

            val rq = Request(input)

            when (rq.actionName) {
                "등록" -> wiseSayingController.write()
                "목록" -> wiseSayingController.print(rq)
                "수정" -> wiseSayingController.modify(rq)
                "삭제" -> wiseSayingController.delete(rq)
                "빌드" -> wiseSayingController.build()
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