package solutions

import org.junit.Test
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.test.assertEquals

class Day04Test {
    @Test
    fun `part 1 returns a correct result`() {
        val input = "[1518-11-01 00:00] Guard #10 begins shift\n" +
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
        val correctOutput = "24"

        assertEquals(correctOutput, Day04.solvePart1(input))
    }

    @Test
    fun `part 2 returns a correct result`() {
        TODO("Implement this!")
    }

    @Test
    fun `record timestamps are parsed correctly`() {
        val input = "[1518-11-01 00:21] Guard #10 begins shift"
        val correctOutput = LocalDateTime.parse("1518-11-01 00:21", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM"))

        assertEquals(correctOutput, Day04.parseTimestamp(input))
    }

    @Test
    fun `three subsequent record lines are parsed correctly`() {
        val input = "[1518-11-01 00:00] Guard #10 begins shift\n" +
                "[1518-11-01 00:05] falls asleep\n" +
                "[1518-11-01 00:25] wakes up"
        val correctOutput: ArrayList<GuardRecord> = arrayListOf(
            GuardRecord(
                timestamp = Day04.parseTimestamp("[1518-11-01 00:00]"),
                id = 10,
                action = GuardAction.BEGINS_SHIFT
            ),
            GuardRecord(
                timestamp = Day04.parseTimestamp("[1518-11-01 00:05]"),
                id = 10,
                action = GuardAction.FALLS_ASLEEP
            ),
            GuardRecord(
                timestamp = Day04.parseTimestamp("[1518-11-01 00:25]"),
                id = 10,
                action = GuardAction.WAKES_UP
            )
        )

        assertEquals(correctOutput, Day04.parseRecordLines(input))
    }
}
