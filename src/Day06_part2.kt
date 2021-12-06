private const val maxDays = 256
private val calculationPeriod = 0 until maxDays

fun main() {
    fun part2(input: String): Long {
        val fishInitialTimers = input.split(',').map { it.toInt() }

        val initialCountByTimers = fishInitialTimers.groupingBy { it }
            .eachCount()
            .map { it.key to it.value.toLong() }
            .toMap()
//            .toSortedMap()

        return calculationPeriod
            .fold(initialCountByTimers) { countByTimers, _ ->
                val newFishCount = countByTimers[0] ?: 0
                countByTimers
                    .map { timerWithCount ->
                        when (timerWithCount.key) {
                            0 -> {
                                initialTimerAfterBirthGiven to timerWithCount.value
                            }
                            initialTimerAfterBirthGiven + 1 -> {
                                timerWithCount.key - 1 to (countByTimers[lifecycleEndTimer] ?: 0) + timerWithCount.value
                            }
                            else -> {
                                timerWithCount.key - 1 to timerWithCount.value
                            }
                        }
                    }
                    .toMap()
                    .plus(initialTimerForNewBorn to newFishCount)
            }
            .map { it.value }
            .sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(testInput.size == 1) { "Input size is not valid" }
    val part2Result = part2(testInput[0])
    println(part2Result)
    check(part2Result == 26984457539) { "part2 failed: $part2Result" }

    val input = readInput("Day06")
    check(input.size == 1) { "Input size is not valid" }
    val part2FinalResult = part2(input[0])
    println("part2: $part2FinalResult")
    check(part2FinalResult == 1708791884591L) { "part2 Final failed: $part2FinalResult" }
}