package solutions

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

const val RecordTimestampPattern = "yyyy-MM-dd HH:mm"
const val GuardIdRegex = "#[0-9]*"
const val BeginsShiftRegex = "begins shift"
const val WakesUpRegex = "wakes up"
const val FallsAsleepRegex = "falls asleep"

enum class GuardAction {
    BEGINS_SHIFT, FALLS_ASLEEP, WAKES_UP
}

data class GuardRecord(
    val id: Int,
    val timestamp: LocalDateTime,
    val action: GuardAction
)

data class GuardAsleepTime(
    val fellAsleepAt: LocalDateTime,
    val wokeUpAt: LocalDateTime
)

object Day04 : Solution() {
    override val identifier = this::class.simpleName.toString()

    override fun solvePart1(input: String): String {
        val lines: List<String> = splitMultilineInput(input)
        val recordEntries: ArrayList<Pair<LocalDateTime, String>> = orderRecordEntries(lines)
        val guardRecords: ArrayList<GuardRecord> = parseRecordEntries(recordEntries)
        val guardsAsleepTimes: HashMap<Int, ArrayList<GuardAsleepTime>> = accumulateGuardsAsleepTimes(guardRecords)
        val guardThatIsAsleepTheMost: Int = calculateGuardWithMostTimeAsleep(guardsAsleepTimes)
        val minuteGuardIsAsleepTheMost: Int =
            determineMinuteWhichGuardIsAsleepTheMost(guardsAsleepTimes[guardThatIsAsleepTheMost]!!)

        return (guardThatIsAsleepTheMost * minuteGuardIsAsleepTheMost).toString()
    }

    fun determineMinuteWhichGuardIsAsleepTheMost(guardsAsleepTimes: ArrayList<GuardAsleepTime>): Int {
        val minuteArray: Array<Int> = buildAsleepMinuteArray(guardsAsleepTimes)
        val mostAsleepCount: Int? = minuteArray.max()
        return minuteArray.indexOf(mostAsleepCount)
    }

    fun buildAsleepMinuteArray(guardsAsleepTimes: ArrayList<GuardAsleepTime>): Array<Int> {
        val minuteArray: Array<Int> = Array(60) { 0 }

        for (asleepTime: GuardAsleepTime in guardsAsleepTimes) {
            for (minute: Int in asleepTime.fellAsleepAt.minute..(asleepTime.wokeUpAt.minute - 1)) {
                minuteArray[minute]++
            }
        }

        return minuteArray
    }

    fun calculateGuardWithMostTimeAsleep(guardsAsleepTimes: HashMap<Int, ArrayList<GuardAsleepTime>>): Int {
        val guardsTotalAsleepTimes: HashMap<Int, Long> = HashMap()

        for (asleepTime: MutableMap.MutableEntry<Int, ArrayList<GuardAsleepTime>> in guardsAsleepTimes) {
            val totalTimeAsleep: Long =
                asleepTime.value
                    .asSequence()
                    .map { it.fellAsleepAt.until(it.wokeUpAt, ChronoUnit.MINUTES) }
                    .sum()

            guardsTotalAsleepTimes[asleepTime.key] = (guardsTotalAsleepTimes[asleepTime.key] ?: 0) + totalTimeAsleep
        }

        return guardsTotalAsleepTimes.maxBy { it.value }!!.key
    }

    fun accumulateGuardsAsleepTimes(guardRecords: ArrayList<GuardRecord>): HashMap<Int, ArrayList<GuardAsleepTime>> {
        val guardsAsleepTimes: HashMap<Int, ArrayList<GuardAsleepTime>> = HashMap()
        var fellAsleepAt: LocalDateTime = LocalDateTime.MIN

        for (record: GuardRecord in guardRecords) {
            when {
                record.action == GuardAction.FALLS_ASLEEP -> {
                    fellAsleepAt = record.timestamp
                }
                record.action == GuardAction.WAKES_UP -> {
                    val wokeUpAt: LocalDateTime = record.timestamp
                    if (!guardsAsleepTimes.containsKey(record.id)) guardsAsleepTimes[record.id] = ArrayList()
                    guardsAsleepTimes[record.id]?.add(GuardAsleepTime(fellAsleepAt, wokeUpAt))
                }
            }
        }

        return guardsAsleepTimes
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
        val lines: List<String> = splitMultilineInput(input)
        val recordEntries: ArrayList<Pair<LocalDateTime, String>> = orderRecordEntries(lines)
        val guardRecords: ArrayList<GuardRecord> = parseRecordEntries(recordEntries)
        val guardsAsleepTimes: HashMap<Int, ArrayList<GuardAsleepTime>> = accumulateGuardsAsleepTimes(guardRecords)
        val guardSleepDurationTimes: HashMap<Int, Array<Int>> = buildGuardSleepDurationTimes(guardsAsleepTimes)
        val guardAndMinuteTheyAreMostAsleepIn =
            determineGuardWhichIsAsleepTheMostInAParticularMinute(guardSleepDurationTimes)

        return (guardAndMinuteTheyAreMostAsleepIn.first * guardAndMinuteTheyAreMostAsleepIn.second).toString()
    }

    fun determineGuardWhichIsAsleepTheMostInAParticularMinute(guardSleepDurationTimes: HashMap<Int, Array<Int>>): Pair<Int, Int> {
        val guardThatSleepsTheMostInParticularMinute: Pair<Int, Int> = guardSleepDurationTimes.asSequence()
            .map { (key: Int, value: Array<Int>) -> key to value.max()!! }
            .maxBy { it.second }!!

        val minuteThatGuardIsAsleepTheMostIn: Int = guardSleepDurationTimes.asSequence()
            .find { (key: Int, _) -> key == guardThatSleepsTheMostInParticularMinute.first }!!
            .value
            .indexOf(guardThatSleepsTheMostInParticularMinute.second)

        return Pair(guardThatSleepsTheMostInParticularMinute.first, minuteThatGuardIsAsleepTheMostIn)
    }

    fun buildGuardSleepDurationTimes(guardsAsleepTimes: HashMap<Int, ArrayList<GuardAsleepTime>>): HashMap<Int, Array<Int>> {
        val guardSleepDurationTimes: HashMap<Int, Array<Int>> = HashMap()

        for (guard: MutableMap.MutableEntry<Int, ArrayList<GuardAsleepTime>> in guardsAsleepTimes) {
            val guardId = guard.key
            for (asleepTime: GuardAsleepTime in guard.value) {
                // Initialize the array of each guard which each minute
                if (!guardSleepDurationTimes.containsKey(guardId)) guardSleepDurationTimes[guardId] = Array(60) { 0 }
                for (minute: Int in asleepTime.fellAsleepAt.minute..(asleepTime.wokeUpAt.minute - 1)) {
                    guardSleepDurationTimes[guardId]!![minute]++
                }
            }
        }

        return guardSleepDurationTimes
    }
}

fun main(args: Array<String>) {
    Day04.solve()
}
