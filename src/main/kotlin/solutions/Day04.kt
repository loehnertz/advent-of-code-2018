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

    fun calculateGuardWithMostTimeAsleep(guardAsleepTimes: HashMap<Int, Array<Int>>): Pair<Int, Int> {
        TODO("Implement this!")
    }

    fun orderRecordEntries(recordLines: List<String>): ArrayList<Pair<LocalDateTime, String>> {
        TODO("not implemented")
    }

    fun parseRecordEntries(recordEntries: ArrayList<Pair<LocalDateTime, String>>): ArrayList<GuardRecord> {
        TODO("Implement this!")
    }

    fun parseTimestamp(timestamp: String): LocalDateTime {
        return LocalDateTime.parse(timestamp, DateTimeFormatter.ofPattern(RecordTimestampPattern))
    }

    override fun solvePart2(input: String): String {
        TODO("Implement this!")
    }
}

fun main(args: Array<String>) {
    Day04.solve()
}
