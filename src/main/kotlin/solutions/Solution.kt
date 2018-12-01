package solutions

abstract class Solution {
    private val relativeInputDirectoryPath = "src/main/resources/inputs"

    abstract val identifier: String

    abstract fun solve(): String
}
