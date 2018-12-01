package solutions

import java.util.*

object Day01 : Solution() {
    override val identifier = this::class.simpleName.toString()

    override fun solvePart1(input: String): String {
        val splitInput: List<String> = input.split("\n")
        var frequency = 0

        for (line: String in splitInput) {
            val operation = parseArithmeticOperation(line)
            frequency += operation
        }

        return frequency.toString()
    }

    override fun solvePart2(input: String): String {
        val splitInput: List<String> = input.split("\n")
        val repeatedInput: List<String> = Collections.nCopies(100000, splitInput).flatten()
        var frequency = 0
        val seenFrequencies: MutableSet<Int> = mutableSetOf(frequency)

        for (line: String in repeatedInput) {
            val operation = parseArithmeticOperation(line)
            frequency += operation

            if (seenFrequencies.contains(frequency)) return frequency.toString()
            seenFrequencies.add(frequency)
        }

        throw NoSuchElementException("Could not find any frequency pattern")
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
