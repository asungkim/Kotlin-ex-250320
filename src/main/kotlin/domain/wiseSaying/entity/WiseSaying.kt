package domain.wiseSaying.entity

data class WiseSaying(
    val id: Int = 0,
    var saying: String,
    var author: String
) {
    val jsonStr: String
        get() = """
            {
                "id":$id,
                "saying":$saying,
                "author":$author
            }
        """.trimIndent()

    fun isNew(): Boolean {
        return id == 0
    }
}