package standard

object JsonUtil {

    fun listToJson(mapList: List<Map<String, Any>>): String {
        return mapList.joinToString(
            prefix = "[\n", postfix = "\n]", separator = ",\n"
        ) {
            mapToJson(it).prependIndent("    ")
        }
    }

    fun mapToJson(map: Map<String, Any>): String {
        return map.entries.joinToString(
            prefix = "{\n", postfix = "\n}", separator = ",\n"
        ) { (key, value) ->
            val formatedKey = "\"${key}\""
            val formatedValue = when (value) {
                is String -> "\"${value}\""
                else -> value
            }

            "    ${formatedKey}: ${formatedValue}".toString()
        }
    }

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