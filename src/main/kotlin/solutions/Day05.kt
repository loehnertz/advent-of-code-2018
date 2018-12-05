package solutions

const val deleteChar = "-"

object Day05 : Solution() {
    override val identifier = this::class.simpleName.toString()

    override fun solvePart1(input: String): String {
        val initialPolymer: ArrayList<String> = ArrayList(input.split(""))
        return collapsePolymer(initialPolymer).size.toString()
    }

    fun collapsePolymer(initialPolymer: ArrayList<String>): ArrayList<String> {
        // Mark the first and last character to start the while-loop as it is an empty character anyway
        initialPolymer[0] = deleteChar
        initialPolymer[initialPolymer.size - 1] = deleteChar

        while (initialPolymer.indexOf(deleteChar) != -1) {
            // Remove all characters that were marked in the last iteration
            initialPolymer.removeIf { it == deleteChar }

            for (char in 0..(initialPolymer.size - 1)) {
                // Remove the current and the next character when they are units of opposite polarity
                if (char != (initialPolymer.size - 1)) {
                    val currentUnit: Char = initialPolymer[char].toCharArray().first()
                    val nextUnit: Char = initialPolymer[char + 1].toCharArray().first()

                    if (currentUnit.equals(other = nextUnit, ignoreCase = true) && currentUnit != nextUnit) {
                        initialPolymer[char] = deleteChar
                        initialPolymer[char + 1] = deleteChar
                    }
                }
            }
        }

        return initialPolymer
    }

    override fun solvePart2(input: String): String {
        TODO("Implement this!")
    }
}

fun main(args: Array<String>) {
    Day05.solve()
}
