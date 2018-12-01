package solutions

import java.io.File
import java.io.InputStream
import java.nio.file.Paths

abstract class Solution {
    private val relativeInputDirectoryPath = "src/main/resources/inputs"

    abstract val identifier: String

    abstract fun solve(): String

    fun retrieveInput(): String {
        val inputDirectoryPath = Paths.get("").resolve(relativeInputDirectoryPath).toAbsolutePath().toString()
        val inputStream: InputStream = File("$inputDirectoryPath/$identifier.txt").inputStream()
        return inputStream.bufferedReader().use { it.readText() }
    }
}
