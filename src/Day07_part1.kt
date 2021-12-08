import kotlin.math.abs

fun main() {
    fun part1(horizontalPositions: List<Int>): Int {
        val (minimumHorizontalPosition, maximumHorizontalPosition) = horizontalPositions
            .sorted()
            .let { sorterPositions -> (sorterPositions.minOrNull() ?: 0) to (sorterPositions.maxOrNull() ?: 0) }

        return (minimumHorizontalPosition..maximumHorizontalPosition)
            .map { positionToCheck ->
                horizontalPositions
                    .sumOf { crabPosition -> abs(positionToCheck - crabPosition) }

            }
            .minOrNull() ?: 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(testInput.size == 1) { "Input size is not valid" }
    val part1Result = part1(testInput[0].split(',').map { it.toInt() })
    println(part1Result)
    check(part1Result == 37) { "Part1 failed: $part1Result" }

    val input = readInput("Day07")
    check(input.size == 1) { "Input size is not valid" }
    val part1FinalResult = part1(input[0].split(',').map { it.toInt() })
    println("Part1: $part1FinalResult")
    check(part1FinalResult == 356992) { "Part1 Final failed: $part1FinalResult" }
}

