package domain.wiseSaying.entity

import standard.Util

data class WiseSaying(
    val id: Int = 0,
    var content: String,
    var author: String
) {
    val jsonStr: String
        get() = """
            {
                "id":$id,
                "content":"$content",
                "author":"$author"
            }
        """.trimIndent()

    fun isNew(): Boolean {
        return id == 0
    }

    companion object {
        fun fromJson(jsonStr: String): WiseSaying {
            val jsonMap = Util.jsonStrToMap(jsonStr)
            val id = jsonMap["id"] as Int
            val content = jsonMap["content"] as String
            val author = jsonMap["author"] as String

            return WiseSaying(id, content, author)
        }
    }
}