package solutions

const val deleteChar = "-"

object Day05 : Solution() {
    override val identifier = this::class.simpleName.toString()

    override fun solvePart1(input: String): String {
        val inputList: ArrayList<String> = ArrayList(input.split(""))
        // Mark the first and last character to start the while-loop as it is an empty character anyway
        inputList[0] = deleteChar
        inputList[inputList.size - 1] = deleteChar

        while (inputList.indexOf(deleteChar) != -1) {
            // Remove all characters that were marked in the last iteration
            inputList.removeIf { it == deleteChar }

            for (char in 0..(inputList.size - 1)) {
                // Remove the current and the next character when they are units of opposite polarity
                if (char != (inputList.size - 1)) {
                    val currentUnit: Char = inputList[char].toCharArray().first()
                    val nextUnit: Char = inputList[char + 1].toCharArray().first()

                    if (currentUnit.equals(other = nextUnit, ignoreCase = true) && currentUnit != nextUnit) {
                        inputList[char] = deleteChar
                        inputList[char + 1] = deleteChar
                    }
                }
            }
        }

        return inputList.size.toString()
    }

    override fun solvePart2(input: String): String {
        TODO("Implement this!")
    }
}

fun main(args: Array<String>) {
    Day05.solve()
}
