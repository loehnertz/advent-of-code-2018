package solutions

import java.time.LocalDateTime

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
        TODO("Not implemented yet.")
    }

    override fun solvePart2(input: String): String {
        TODO("Not implemented yet.")
    }
}

fun main(args: Array<String>) {
    Day04.solve()
}
