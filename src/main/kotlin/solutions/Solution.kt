package solutions

import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream
import java.nio.file.Paths

const val relativeInputDirectoryPath = "src/main/resources/inputs"

abstract class Solution {
    abstract val identifier: String

    abstract fun solvePart1(input: String): String

    abstract fun solvePart2(input: String): String

    fun solve() {
        println("Solution for part 1: " + solvePart1(retrieveInput()))
        println("Solution for part 2: " + solvePart2(retrieveInput()))
    }

    fun retrieveInput(): String {
        val inputDirectoryPath = Paths.get("").resolve(relativeInputDirectoryPath).toAbsolutePath().toString()
        return try {
            val inputStream: InputStream = File("$inputDirectoryPath/$identifier.txt").inputStream()
            inputStream.bufferedReader().use { it.readText() }
        } catch (e: FileNotFoundException) {
            ""
        }
    }
}
