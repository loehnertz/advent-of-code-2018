package solutions

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class Day01Test {
    @Test
    fun `valid input of part 1 gets solved correctly`() {
        val input = "+1\n-10\n+21\n+5\n+6"
        val correctOutput = "23"
        assertEquals(correctOutput, Day01.solvePart1(input))
    }

    @Test
    fun `valid input of part 1 that does not to be repeated gets solved correctly`() {
        val input = "+1\n-10\n+21\n+5\n+6\n-23"
        val correctOutput = "0"
        assertEquals(correctOutput, Day01.solvePart2(input))
    }

    @Test
    fun `valid input of part 1 that needs to be repeated gets solved correctly`() {
        val input = "+1\n-10\n+21\n+5\n+6\n-21"
        val correctOutput = "23"
        assertEquals(correctOutput, Day01.solvePart2(input))
    }

    @Test
    fun `arithmetic operations are parsed correctly`() {
        assertEquals(+10, Day01.parseArithmeticOperation("+10"))
        assertEquals(-20, Day01.parseArithmeticOperation("-20"))
        assertFailsWith(UnsupportedOperationException::class) { Day01.parseArithmeticOperation("*20") }
    }
}
