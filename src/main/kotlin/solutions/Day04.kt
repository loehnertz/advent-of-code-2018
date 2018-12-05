package solutions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val RecordTimestampPattern = "yyyy-MM-dd HH:mm"
const val GuardIdRegex = "#[0-9]*"
const val BeginsShiftRegex = "begins shift"
const val WakesUpRegex = "wakes up"
const val FallsAsleepRegex = "falls asleep"

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
        val lines: List<String> = splitMultilineInput(input)
        val recordEntries: ArrayList<Pair<LocalDateTime, String>> = orderRecordEntries(lines)
        val guardRecords: ArrayList<GuardRecord> = parseRecordEntries(recordEntries)

        return ""
    }

    fun calculateGuardWithMostTimeAsleep(guardAsleepTimes: HashMap<Int, Array<Int>>): Pair<Int, Int> {
        TODO("Implement this!")
    }

    fun parseRecordEntries(recordEntries: ArrayList<Pair<LocalDateTime, String>>): ArrayList<GuardRecord> {
        val guardRecords: ArrayList<GuardRecord> = ArrayList()
        var currentAction = GuardAction.BEGINS_SHIFT
        var currentGuardId = 0

        for (entry: Pair<LocalDateTime, String> in recordEntries) {
            val stringToParse: String = entry.second

            when {
                // Guards begins shift
                stringToParse.contains(Regex(BeginsShiftRegex)) -> {
                    currentAction = GuardAction.BEGINS_SHIFT
                    currentGuardId = Regex(GuardIdRegex).find(stringToParse)?.value.toString().replace("#", "").toInt()
                }
                // Guards wakes up
                stringToParse.contains(Regex(WakesUpRegex)) -> {
                    currentAction = GuardAction.WAKES_UP
                }
                // Guards falls asleep
                stringToParse.contains(Regex(FallsAsleepRegex)) -> {
                    currentAction = GuardAction.FALLS_ASLEEP
                }
            }

            guardRecords.add(
                GuardRecord(
                    timestamp = entry.first,
                    id = currentGuardId,
                    action = currentAction
                )
            )
        }

        return guardRecords
    }

    fun orderRecordEntries(recordLines: List<String>): ArrayList<Pair<LocalDateTime, String>> {
        val recordEntries: ArrayList<Pair<LocalDateTime, String>> = ArrayList()

        for (line in recordLines) {
            var (stringTimestamp, restOfEntry) = line.split("]")
            // The timestamp still has a leading [ after the splitting
            stringTimestamp = stringTimestamp.replace("[", "")
            restOfEntry = restOfEntry.removeRange(0..0)

            recordEntries.add(Pair(parseTimestamp(stringTimestamp), restOfEntry))
        }

        recordEntries.sortBy { it.first }
        return recordEntries
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
