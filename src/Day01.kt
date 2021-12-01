fun main() {
    fun part1(input: List<Int>): Int {

        return return input
            .windowed(2, 1)
            .filter { it[0] < it[1] }.size
    }

    fun part2(input: List<Int>): Int {

        return input
            .windowed(3, 1) { window -> window.sum() }
            .windowed(2, 1)
            .filter { it[0] < it[1] }.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test").map { it.toInt() }
    val part1Result = part1(testInput)
    println(part1Result)
    check(part1Result == 7) { "Part1 failed: $part1Result" }
    val part2Result = part2(testInput)
    check(part2Result == 5) { "Part2 failed: $part2Result" }

    val input = readInput("Day01").map { it.toInt() }
    println("Part1: ${part1(input)}")
    println("Part2: ${part2(input)}")
}