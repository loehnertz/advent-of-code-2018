package solutions

const val deleteChar = "-"

object Day05 : Solution() {
    override val identifier = this::class.simpleName.toString()

    override fun solvePart1(input: String): String {
        val initialPolymer: ArrayList<String> = ArrayList(input.split(""))
        initialPolymer.removeIf { it == "" }  // Kotlin adds two empty characters to front and back for some reason
        return collapsePolymer(initialPolymer).size.toString()
    }

    fun collapsePolymer(initialPolymer: ArrayList<String>): ArrayList<String> {
        do {
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
        } while (initialPolymer.indexOf(deleteChar) != -1)

        return initialPolymer
    }

    override fun solvePart2(input: String): String {
        val initialPolymer: ArrayList<String> = ArrayList(input.split(""))
        initialPolymer.removeIf { it == "" }  // Kotlin adds two empty characters to front and back for some reason
        val allUnitsInPolymer: List<String> = initialPolymer.map { it.toLowerCase() }.distinct()
        val allUnitsInPolymerWithImpactOnCollapsing: ArrayList<Pair<String, Int>> = ArrayList()

        for (unit: String in allUnitsInPolymer) {
            val tempPolymer: ArrayList<String> = ArrayList(initialPolymer)
            tempPolymer.removeIf { it.toLowerCase() == unit }
            val polymerSizeAfterCollapsing: Int = collapsePolymer(tempPolymer).size
            allUnitsInPolymerWithImpactOnCollapsing.add(Pair(unit, polymerSizeAfterCollapsing))
        }

        val unitWithMostImpactOnCollapsing: String? = allUnitsInPolymerWithImpactOnCollapsing.minBy { it.second }?.first
        val tempPolymer: ArrayList<String> = ArrayList(initialPolymer)
        tempPolymer.removeIf { it.toLowerCase() == unitWithMostImpactOnCollapsing }

        return collapsePolymer(tempPolymer).size.toString()
    }
}

fun main(args: Array<String>) {
    Day05.solve()
}
