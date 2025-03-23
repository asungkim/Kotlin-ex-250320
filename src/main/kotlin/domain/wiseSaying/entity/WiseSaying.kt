package domain.wiseSaying.entity

data class WiseSaying(
    val id: Int = 0,
    var saying: String,
    var author: String
) {
    fun isNew(): Boolean {
        return id == 0
    }
}