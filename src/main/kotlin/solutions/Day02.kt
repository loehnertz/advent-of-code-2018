package solutions

const val OccurrencesOfExactlyTwoKey = 2
const val OccurrencesOfExactlyThreeKey = 3

object Day02 : Solution() {
    override val identifier = this::class.simpleName.toString()

    private val occurrencesOfTwoAndThreeLetters: HashMap<Int, Int> = hashMapOf(
        OccurrencesOfExactlyTwoKey to 0,
        OccurrencesOfExactlyThreeKey to 0
    )

    override fun solvePart1(input: String): String {
        val lines: List<String> = splitMultilineInput(input)

        for (line: String in lines) {
            val occurrences: Pair<Boolean, Boolean> =
                calculateLetterOccurrenceScores(countLetterOccurrences(line.split("")))
            updateOccurrenceMap(occurrences)
        }

        return calculateChecksum().toString()
    }

    override fun solvePart2(input: String): String {
        TODO("Not implemented yet.")
    }

    private fun calculateChecksum(): Int {
        return occurrencesOfTwoAndThreeLetters.getValue(
            OccurrencesOfExactlyTwoKey
        ) * occurrencesOfTwoAndThreeLetters.getValue(
            OccurrencesOfExactlyThreeKey
        )
    }

    private fun updateOccurrenceMap(occurrences: Pair<Boolean, Boolean>) {
        if (occurrences.first) occurrencesOfTwoAndThreeLetters[OccurrencesOfExactlyTwoKey] =
                (occurrencesOfTwoAndThreeLetters[OccurrencesOfExactlyTwoKey] ?: 0) + 1
        if (occurrences.second) occurrencesOfTwoAndThreeLetters[OccurrencesOfExactlyThreeKey] =
                (occurrencesOfTwoAndThreeLetters[OccurrencesOfExactlyThreeKey] ?: 0) + 1
    }

    fun calculateLetterOccurrenceScores(occurrences: HashMap<String, Int>): Pair<Boolean, Boolean> {
        var occursExactlyTwice = false
        var occursExactlyThrice = false

        if (occurrences.filter { it.value == 2 }.any()) occursExactlyTwice = true  // A letter occures exactly twice
        if (occurrences.filter { it.value == 3 }.any()) occursExactlyThrice = true  // A letter occurs exactly thrice

        return Pair(occursExactlyTwice, occursExactlyThrice)
    }

    fun countLetterOccurrences(letters: List<String>): HashMap<String, Int> {
        val occurrences = HashMap<String, Int>()

        for (letter: String in letters) {
            occurrences[letter] = (occurrences[letter] ?: 0) + 1
        }
        occurrences.remove("")  // The beginning and end of each line has an 'empty' letter each

        return occurrences
    }
}

fun main(args: Array<String>) {
    Day02.solve()
}
