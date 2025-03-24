package global

class Request(
    val input: String = ""
) {

    val actionName: String
    val paramMap: Map<String, String>

    init {
        val inputBits = input.split("?", limit = 2)
        actionName = inputBits[0]

        paramMap = if (inputBits.size == 2) {
            inputBits[1].split("&").associate {
                val bits = it.split("=", limit = 2)
                bits[0] to bits[1]
            }
        } else {
            emptyMap()
        }
    }

    fun getParam(key: String): String? {
        return paramMap.get(key)
    }
}