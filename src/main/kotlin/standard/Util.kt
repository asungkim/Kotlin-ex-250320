package standard

object Util {
    fun jsonStrToMap(jsonStr: String): Map<String, Any> {
        val replacedJsonStr = jsonStr
            .replace("{", "")
            .replace("}", "")
            .replace("\n", "")
            .replace(" : ", ":")

        val result = replacedJsonStr.split(",").associate { pair ->
            val bits = pair.split(":", limit = 2)

            val key = bits[0].trim().replace("\"", "")
            val value = if (bits[1].startsWith("\"")) {
                bits[1].trim().replace("\"", "")
            } else bits[1].trim().toInt()

            key to value
        }

        return result
    }
}