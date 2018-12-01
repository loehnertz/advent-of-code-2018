package solutions

import org.junit.Test
import java.io.File
import java.nio.file.Paths
import kotlin.test.assertEquals

class SolutionTest {
    @Test
    fun `the content of an input file can be correctly retrieved`() {
        val testFileContent = "Just a test!"
        val testFileName = "DayTest"

        val solutionImplementation = object : Solution() {
            override val identifier = testFileName

            override fun solvePartOne(): String {
                return retrieveInput()
            }

            override fun solvePartTwo(): String {
                return retrieveInput()
            }
        }

        // Create temporary test file
        val inputDirectoryPath = Paths.get("").resolve(relativeInputDirectoryPath).toAbsolutePath().toString()
        File("$inputDirectoryPath/$testFileName.txt").writeText(testFileContent)

        assertEquals("Just a test!", solutionImplementation.solvePartOne())

        // Delete temporary test file
        File("$inputDirectoryPath/$testFileName.txt").delete()
    }
}
