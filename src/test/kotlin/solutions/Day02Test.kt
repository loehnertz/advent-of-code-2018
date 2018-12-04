package solutions

import org.junit.Test
import kotlin.test.assertEquals

class Day02Test {
    @Test
    fun `the occurrences of input letters are counted correctly`() {
        val input: List<String> = "bababc".split("")
        val correctOutput: HashMap<String, Int> = hashMapOf("a" to 2, "b" to 3, "c" to 1)

        assertEquals(correctOutput, Day02.countLetterOccurrences(input))
    }

    @Test
    fun `the letter occurrences for two and three occurrences is calculated correctly`() {
        val input1: HashMap<String, Int> = hashMapOf("a" to 2, "b" to 3, "c" to 1)
        val correctOutput1: Pair<Boolean, Boolean> = Pair(true, true)
        assertEquals(correctOutput1, Day02.calculateLetterOccurrenceScores(input1))

        val input2: HashMap<String, Int> = hashMapOf("a" to 2, "b" to 2, "c" to 1)
        val correctOutput2: Pair<Boolean, Boolean> = Pair(true, false)
        assertEquals(correctOutput2, Day02.calculateLetterOccurrenceScores(input2))

        val input3: HashMap<String, Int> = hashMapOf("a" to 1, "b" to 3, "c" to 1)
        val correctOutput3: Pair<Boolean, Boolean> = Pair(false, true)
        assertEquals(correctOutput3, Day02.calculateLetterOccurrenceScores(input3))

        val input4: HashMap<String, Int> = hashMapOf("a" to 1, "b" to 1, "c" to 1)
        val correctOutput4: Pair<Boolean, Boolean> = Pair(false, false)
        assertEquals(correctOutput4, Day02.calculateLetterOccurrenceScores(input4))
    }

    @Test
    fun `a single differing letter is removed correctly`() {
        val input1 = "fghij"
        val input2 = "fguij"
        val correctOutput = "fgij"

        assertEquals(correctOutput, Day02.removeDifferingLetter(input1, input2))
        assertEquals(correctOutput, Day02.removeDifferingLetter(input2, input1))
    }

    @Test
    fun `it is detected correctly if two given lines are differing by only one letter`() {
        val input1 = "fghij"
        val input2 = "fguij"
        val correctOutput = true

        assertEquals(correctOutput, Day02.lineOnlyDiffersByOneCharacter(input1, input2))
        assertEquals(correctOutput, Day02.lineOnlyDiffersByOneCharacter(input2, input1))
    }

    @Test
    fun `two lines that differ by more than one letter are detected correctly`() {
        val input1 = "fghij"
        val input2 = "fgukj"
        val correctOutput = false

        assertEquals(correctOutput, Day02.lineOnlyDiffersByOneCharacter(input1, input2))
        assertEquals(correctOutput, Day02.lineOnlyDiffersByOneCharacter(input2, input1))
    }
}
