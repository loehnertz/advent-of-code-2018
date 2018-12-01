package solutions

object Day01 : Solution() {
    override val identifier = this::class.simpleName.toString()

    override fun solvePart1(input: String): String {
        val splitInput: List<String> = input.split("\n")
        var initialFrequency = 0

        for (line: String in splitInput) {
            val operation = parseArithmeticOperation(line)
            initialFrequency += operation
        }

        return initialFrequency.toString()
    }

    override fun solvePart2(input: String): String {
        return "Not implemented yet"
    }

    fun parseArithmeticOperation(operation: String): Int {
        val operator: String = operation.substring(0, 1)
        val amount: Int = operation.substring(1).toInt()
        return when (operator) {
            "+" -> +amount
            "-" -> -amount
            else -> throw UnsupportedOperationException("The operator '$operator' is not supported by this function.")
        }
    }
}

fun main(args: Array<String>) {
    Day01.solve()
}
