package solutions

import org.junit.Test
import java.io.File
import java.nio.file.Paths
import kotlin.test.assertEquals

class SolutionTest {
    @Test
    fun `the content of an input file can be correctly retrieved`() {
        val testFileName = "Day00"
        val testFileContent = "Just a test!"

        val solutionImplementation = object : Solution() {
            override val identifier = testFileName

            override fun solvePart1(input: String): String {
                return input
            }

            override fun solvePart2(input: String): String {
                return input
            }
        }

        // Create temporary test file
        val inputDirectoryPath = Paths.get("").resolve(RelativeInputDirectoryPath).toAbsolutePath().toString()
        File("$inputDirectoryPath/$testFileName.$FileExtension").writeText(testFileContent)

        assertEquals(testFileContent, solutionImplementation.retrieveInput())

        // Delete temporary test file
        File("$inputDirectoryPath/$testFileName.$FileExtension").delete()
    }
}
