package solutions

import org.junit.Test
import kotlin.test.assertEquals

class Day05Test {
    @Test
    fun `part 1 returns a correct result`() {
        val input = "dabAcCaCBAcCcaDA"

        val correctOutput = "10"

        assertEquals(correctOutput, Day05.solvePart1(input))
    }

    @Test
    fun `part 2 returns a correct result`() {
        val input = "dabAcCaCBAcCcaDA"

        val correctOutput = "4"

        assertEquals(correctOutput, Day05.solvePart2(input))
    }

    @Test
    fun `a given polymer gets correctly collapsed`() {
        val rawInput = "dabAcCaCBAcCcaDA"
        val input: ArrayList<String> = ArrayList(rawInput.split(""))
        input.removeIf { it == "" }

        val rawCorrectOutput = "dabCBAcaDA"
        val correctOutput: ArrayList<String> = ArrayList(rawCorrectOutput.split(""))
        correctOutput.removeIf { it == "" }

        assertEquals(correctOutput, Day05.collapsePolymer(input))
    }

    @Test
    fun `an uncollapsable polymer is gets not further collapsed`() {
        val rawInput = "dabCBAcaDA"
        val input: ArrayList<String> = ArrayList(rawInput.split(""))
        input.removeIf { it == "" }

        assertEquals(input, Day05.collapsePolymer(input))
    }

    @Test
    fun `the unit with the biggest impact when removed upon collapsing the polymer is correctly detected`() {
        val rawInput = "dabAcCaCBAcCcaDA"
        val input: ArrayList<String> = ArrayList(rawInput.split(""))
        input.removeIf { it == "" }

        val correctOutput = "c"

        assertEquals(correctOutput, Day05.determineUnitWithBiggestCollapsingImpactUponRemovalFromPolymer(input))
    }
}
