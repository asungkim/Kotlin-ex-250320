package domain.wiseSaying.entity

data class WiseSaying(
    val id: Int = 0,
    var content: String,
    var author: String
) {
    val jsonStr: String
        get() = """
            {
                "id":$id,
                "saying":$content,
                "author":$author
            }
        """.trimIndent()

    fun isNew(): Boolean {
        return id == 0
    }
}