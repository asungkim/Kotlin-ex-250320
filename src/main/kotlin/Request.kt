class Request(
    var input: String
) {
    var command: String = ""
    var paramKey: String = ""
    var paramValue: String = ""

    init {
        parseInput()
    }

    private fun parseInput() {
        val inputBits = input.split("?")
        command = inputBits[0]

        if (inputBits.size >= 2) {
            val parts = inputBits[1].split("=")
            paramKey = parts[0]
            paramValue = parts[1]
        }
    }
}