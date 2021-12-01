fun main() {
    fun part1(input: List<Int>): Int {
        
        return input
            .filterIndexed { index, measurement -> index > 0 && measurement > input[index-1] }
            .count()
    }

    fun part2(input: List<Int>): Int {
        val windows = input
            .take(input.size - 2)
            .mapIndexed { index, current -> current + input[index + 1] + input[index + 2] }

        return part1(windows)
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
