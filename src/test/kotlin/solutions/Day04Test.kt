package solutions

import org.junit.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.test.assertEquals

class Day04Test {
    private val dateFormatPattern: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    @Test
    fun `part 1 returns a correct result`() {
        val input: String = "[1518-11-01 00:00] Guard #10 begins shift\n" +
                "[1518-11-01 00:05] falls asleep\n" +
                "[1518-11-01 00:25] wakes up\n" +
                "[1518-11-01 00:30] falls asleep\n" +
                "[1518-11-01 00:55] wakes up\n" +
                "[1518-11-01 23:58] Guard #99 begins shift\n" +
                "[1518-11-02 00:40] falls asleep\n" +
                "[1518-11-02 00:50] wakes up\n" +
                "[1518-11-03 00:05] Guard #10 begins shift\n" +
                "[1518-11-03 00:24] falls asleep\n" +
                "[1518-11-03 00:29] wakes up\n" +
                "[1518-11-04 00:02] Guard #99 begins shift\n" +
                "[1518-11-04 00:36] falls asleep\n" +
                "[1518-11-04 00:46] wakes up\n" +
                "[1518-11-05 00:03] Guard #99 begins shift\n" +
                "[1518-11-05 00:45] falls asleep\n" +
                "[1518-11-05 00:55] wakes up"
        val correctOutput = "240"

        assertEquals(correctOutput, Day04.solvePart1(input))
    }

    @Test
    fun `part 2 returns a correct result`() {
        TODO("Implement this!")
    }

    @Test
    fun `the record lines get correctly chronologically ordered`() {
        val rawInput: String = "[1518-11-01 23:58] Guard #99 begins shift\n" +
                "[1518-11-02 00:40] falls asleep\n" +
                "[1518-11-01 00:00] Guard #10 begins shift"
        val input: List<String> = rawInput.split("\n")

        val timestamp1: LocalDateTime = LocalDateTime.parse("1518-11-01 00:00", dateFormatPattern)
        val timestamp2: LocalDateTime = LocalDateTime.parse("1518-11-01 23:58", dateFormatPattern)
        val timestamp3: LocalDateTime = LocalDateTime.parse("1518-11-02 00:40", dateFormatPattern)

        val correctOutput: ArrayList<Pair<LocalDateTime, String>> = arrayListOf(
            Pair(timestamp1, "Guard #10 begins shift"),
            Pair(timestamp2, "Guard #99 begins shift"),
            Pair(timestamp3, "falls asleep")
        )

        assertEquals(correctOutput, Day04.orderRecordEntries(input))
    }

    @Test
    fun `record timestamps are parsed correctly`() {
        val input = "1518-11-01 00:21"
        val correctOutput: LocalDateTime = LocalDateTime.parse("1518-11-01 00:21", dateFormatPattern)

        assertEquals(correctOutput, Day04.parseTimestamp(input))
    }

    @Test
    fun `three subsequent record lines are parsed correctly`() {
        val rawInput: String = "[1518-11-01 00:00] Guard #10 begins shift\n" +
                "[1518-11-01 00:05] falls asleep\n" +
                "[1518-11-01 00:25] wakes up"
        val input: List<String> = rawInput.split("\n")

        val correctOutput: ArrayList<GuardRecord> = arrayListOf(
            GuardRecord(
                timestamp = LocalDateTime.parse("1518-11-01 00:00", dateFormatPattern),
                id = 10,
                action = GuardAction.BEGINS_SHIFT
            ),
            GuardRecord(
                timestamp = LocalDateTime.parse("1518-11-01 00:05", dateFormatPattern),
                id = 10,
                action = GuardAction.FALLS_ASLEEP
            ),
            GuardRecord(
                timestamp = LocalDateTime.parse("1518-11-01 00:25", dateFormatPattern),
                id = 10,
                action = GuardAction.WAKES_UP
            )
        )

        assertEquals(correctOutput, Day04.parseRecordEntries(Day04.orderRecordEntries(input)))
    }

    @Test
    fun `it is correctly calculated which guard slept the most and in which minute`() {
        val asleepTime1Guard1 = GuardAsleepTime(
            fellAsleepAt = LocalDateTime.parse("1518-11-01 00:25", dateFormatPattern),
            wokeUpAt = LocalDateTime.parse("1518-11-01 00:59", dateFormatPattern)
        )
        val asleepTime2Guard1 = GuardAsleepTime(
            fellAsleepAt = LocalDateTime.parse("1518-11-01 00:25", dateFormatPattern),
            wokeUpAt = LocalDateTime.parse("1518-11-01 00:26", dateFormatPattern)
        )
        val asleepTime1Guard2 = GuardAsleepTime(
            fellAsleepAt = LocalDateTime.parse("1518-11-02 00:01", dateFormatPattern),
            wokeUpAt = LocalDateTime.parse("1518-11-02 00:05", dateFormatPattern)
        )
        val asleepTime2Guard2 = GuardAsleepTime(
            fellAsleepAt = LocalDateTime.parse("1518-11-01 00:25", dateFormatPattern),
            wokeUpAt = LocalDateTime.parse("1518-11-01 00:30", dateFormatPattern)
        )

        val input: HashMap<Int, ArrayList<GuardAsleepTime>> = hashMapOf(
            1 to arrayListOf(asleepTime1Guard1, asleepTime2Guard1),
            2 to arrayListOf(asleepTime1Guard2, asleepTime2Guard2)
        )

        val correctOutputGuardId = 1
        val correctOutputMinute = 25

        val outputGuardId = Day04.calculateGuardWithMostTimeAsleep(input)
        val outputMinute = Day04.determineMinuteWhichGuardIsAsleepTheMost(input[outputGuardId]!!)

        assertEquals(correctOutputGuardId, outputGuardId)
        assertEquals(correctOutputMinute, outputMinute)
    }
}
