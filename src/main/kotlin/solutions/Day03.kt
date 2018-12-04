package solutions

object Day03 : Solution() {
    override val identifier = this::class.simpleName.toString()

    private val fabricCounts = Array(1000) { IntArray(1000) }

    override fun solvePart1(input: String): String {
        val lines: List<String> = splitMultilineInput(input)
        var claimedSquareInches = 0

        for (line in lines) {
            val (_: String, position: Pair<Int, Int>, size: Pair<Int, Int>) = parseLine(line)
            updateFabricPositions(position, size)
        }

        for (row: IntArray in fabricCounts) {
            claimedSquareInches += row.asSequence().filter { it >= 2 }.count()
        }

        return claimedSquareInches.toString()
    }

    private fun updateFabricPositions(position: Pair<Int, Int>, size: Pair<Int, Int>) {
        for (row in position.first..(position.first + size.first - 1)) {
            for (column in position.second..(position.second + size.second - 1)) {
                fabricCounts[row][column] += 1
            }
        }
    }

    fun parseLine(line: String): Triple<String, Pair<Int, Int>, Pair<Int, Int>> {
        var (id, _, position, size) = line.split(" ")

        position = position.replace(":", "")  // A colon is at the end of the positional information
        val (leftOffset, topOffset) = position.split(",")

        val (width, height) = size.split("x")

        return Triple(id, Pair(leftOffset.toInt(), topOffset.toInt()), Pair(width.toInt(), height.toInt()))
    }

    override fun solvePart2(input: String): String {
        val lines: List<String> = splitMultilineInput(input)

        for (line in lines) {
            val (id: String, position: Pair<Int, Int>, size: Pair<Int, Int>) = parseLine(line)
            if (findUniqueFabricIdSpace(size, position)) return id
        }

        throw NoSuchElementException("No ID was found that was not overlapping.")
    }

    private fun findUniqueFabricIdSpace(size: Pair<Int, Int>, position: Pair<Int, Int>): Boolean {
        val desiredIdSquareInches = size.first * size.second
        var currentIdSquareInches = 0

        for (row in position.first..(position.first + size.first - 1)) {
            for (column in position.second..(position.second + size.second - 1)) {
                if (fabricCounts[row][column] == 1) currentIdSquareInches++
                if (currentIdSquareInches == desiredIdSquareInches) return true
            }
        }

        return false
    }
}

fun main(args: Array<String>) {
    Day03.solve()
}
