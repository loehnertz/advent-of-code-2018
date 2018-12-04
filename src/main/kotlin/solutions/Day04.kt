package solutions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val RecordTimestampPattern = "yyyy-MM-dd HH:mm"

enum class GuardAction {
    BEGINS_SHIFT, FALLS_ASLEEP, WAKES_UP
}

data class GuardRecord(
    val timestamp: LocalDateTime,
    val id: Int,
    val action: GuardAction
)

object Day04 : Solution() {
    override val identifier = this::class.simpleName.toString()

    override fun solvePart1(input: String): String {
        TODO("Implement this!")
    }

    fun parseRecordLines(recordLines: List<String>): ArrayList<GuardRecord> {
        TODO("Implement this!")
    }

    fun parseTimestamp(timestampWithBrackets: String): LocalDateTime {
        TODO("Implement this!")
    }

    override fun solvePart2(input: String): String {
        TODO("Implement this!")
    }
}

fun main(args: Array<String>) {
    Day04.solve()
}
