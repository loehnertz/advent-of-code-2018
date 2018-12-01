package solutions

import java.io.File
import java.io.InputStream
import java.nio.file.Paths

const val relativeInputDirectoryPath = "src/main/resources/inputs"

abstract class Solution {
    abstract val identifier: String

    abstract fun solvePartOne(): String

    abstract fun solvePartTwo(): String

    fun retrieveInput(): String {
        val inputDirectoryPath = Paths.get("").resolve(relativeInputDirectoryPath).toAbsolutePath().toString()
        val inputStream: InputStream = File("$inputDirectoryPath/$identifier.txt").inputStream()
        return inputStream.bufferedReader().use { it.readText() }
    }
}
