import kotlin.math.abs

fun main() {
    fun part2(horizontalPositions: List<Int>): Int {
        val (minimumHorizontalPosition, maximumHorizontalPosition) = horizontalPositions
            .sorted()
            .let { sorterPositions -> (sorterPositions.minOrNull() ?: 0) to (sorterPositions.maxOrNull() ?: 0) }

        return (minimumHorizontalPosition..maximumHorizontalPosition)
            .map { positionToCheck ->
                horizontalPositions
                    .sumOf { crabPosition ->
                        val distance = abs(positionToCheck - crabPosition)
                        (distance * (distance + 1)) / 2
                    }

            }
            .minOrNull() ?: 0
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(testInput.size == 1) { "Input size is not valid" }
    val part2Result = part2(testInput[0].split(',').map { it.toInt() })
    println(part2Result)
    check(part2Result == 168) { "part2 failed: $part2Result" }

    val input = readInput("Day07")
    check(input.size == 1) { "Input size is not valid" }
    val part2FinalResult = part2(input[0].split(',').map { it.toInt() })
    println("part2: $part2FinalResult")
    check(part2FinalResult == 101268110) { "part2 Final failed: $part2FinalResult" }
}