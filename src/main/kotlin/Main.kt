var lastIdx = 0
val wiseSayingList = mutableListOf<WiseSaying>()

fun main() {

    println("== 명언 앱 ==")




    while (true) {
        print("명령) ")
        val input = readlnOrNull() ?: ""

        val request = Request(input)
        val command = request.command
        val key = request.paramKey
        val value = request.paramValue

        when (command) {
            "등록" -> register()
            "목록" -> printList()
            "삭제" -> delete(key, value)
            "수정" -> {

            }
            "종료" -> break
            else -> println("허용되지 않은 명령어 입니다. 다시 입력해주세요.")
        }
    }
}

private fun printList() {
    println("번호 / 작가 / 명언")
    println("----------------------")
    wiseSayingList.reversed().forEach {
        println("${it.id} / ${it.author} / ${it.saying}")
    }
}

private fun register() {
    var lastIdx1 = lastIdx
    print("명언 : ")
    val saying = readlnOrNull() ?: ""
    print("작가 : ")
    val author = readlnOrNull() ?: ""
    println("${++lastIdx1}번 명언이 등록되었습니다.")

    wiseSayingList.add(WiseSaying(lastIdx1, saying, author))
}

private fun delete(key: Any, value: Any) {
    val isKeyValid = key in listOf("id", "saying", "author")
    if (!isKeyValid) {
        println("$key 값은 존재하지 않습니다.")
        return
    }

    val iterator = wiseSayingList.iterator()
    var deleted = false

    while (iterator.hasNext()) {
        val wiseSaying = iterator.next()
        val propertyValue = when (key) {
            "id" -> wiseSaying.id.toString()
            "saying" -> wiseSaying.saying
            "author" -> wiseSaying.author
            else -> null
        }

        if (propertyValue == value) {
            iterator.remove()
            println("명언에서 ${key}=${value}에 해당하는 명언이 삭제되었습니다.")
            deleted = true
            break
        }
    }

    if (!deleted) {
        println("$key=$value 에 해당하는 명언은 존재하지 않습니다.")
    }
}