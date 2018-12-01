package solutions

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class Day01Test {
    @Test
    fun `arithmetic operations are parsed correctly`() {
        assertEquals(+10, Day01.parseArithmeticOperation("+10"))
        assertEquals(-20, Day01.parseArithmeticOperation("-20"))
        assertFailsWith(UnsupportedOperationException::class) { Day01.parseArithmeticOperation("*20") }
    }
}
