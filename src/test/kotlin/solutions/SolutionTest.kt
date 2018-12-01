package solutions

import org.testng.Assert.assertEquals
import org.testng.annotations.Test
import java.io.File
import java.nio.file.Paths

class SolutionTest {
    @Test
    fun `the content of an input file can be correctly retrieved`() {
        val testFileContent = "Just a test!"
        val testFileName = "DayTest"

        val solutionImplementation = object : Solution() {
            override val identifier = testFileName

            override fun solve(): String {
                return retrieveInput()
            }
        }

        // Create temporary test file
        val inputDirectoryPath = Paths.get("").resolve(relativeInputDirectoryPath).toAbsolutePath().toString()
        File("$inputDirectoryPath/$testFileName.txt").writeText(testFileContent)

        assertEquals("Just a test!", solutionImplementation.solve())

        // Delete temporary test file
        File("$inputDirectoryPath/$testFileName.txt").delete()
    }
}
