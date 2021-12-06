private const val maxDays = 80
private const val initialTimerAfterBirthGiven = 6
private const val initialTimerForNewBorn = 8
private val calculationPeriod = 0 until maxDays

fun main() {
    fun part1(input: String): Int {
        val fishInitialTimers = input.split(',').map { it.toInt() }

        return calculationPeriod
            .fold(fishInitialTimers.toMutableList()) { timers, day ->
                val newFishCount = timers.count { timer -> timer == 0 }
                timers
                    .map { if (it == 0) initialTimerAfterBirthGiven else it - 1 }
                    .let { it.plus(List(newFishCount) { initialTimerForNewBorn }) }
                    .toMutableList()
            }
            .count()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(testInput.size == 1) { "Input size is not valid" }
    val part1Result = part1(testInput[0])
    println(part1Result)
    check(part1Result == 5934) { "Part1 failed: $part1Result" }

    val inputPart1 = readInput("Day06")
    check(inputPart1.size == 1) { "Input size is not valid" }
    val part1FinalResult = part1(inputPart1[0])
    println("Part1: $part1FinalResult")
    check(part1FinalResult == 380243) { "Part1 Final failed: $part1FinalResult" }
}