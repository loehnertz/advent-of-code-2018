package solutions

import org.junit.Test
import kotlin.test.assertEquals

class Day03Test {
    @Test
    fun `part 1 returns a correct result`() {
        val input = "#1 @ 1,3: 4x4\n#2 @ 3,1: 4x4\n#3 @ 5,5: 2x2"
        val correctOutput = "4"

        assertEquals(correctOutput, Day03.solvePart1(input))
    }

    @Test
    fun `part 2 returns a correct result`() {
        val input = "#1 @ 1,3: 4x4\n#2 @ 3,1: 4x4\n#3 @ 5,5: 2x2"
        val correctOutput = "#3"

        assertEquals(correctOutput, Day03.solvePart2(input))
    }

    @Test
    fun `coordinates of a fabric ID are parsed correctly`() {
        val input = "#1 @ 1,9: 5x6"
        val correctOutput: Triple<String, Pair<Int, Int>, Pair<Int, Int>> = Triple("#1", Pair(1, 9), Pair(5, 6))

        assertEquals(correctOutput, Day03.parseLine(input))
    }
}
