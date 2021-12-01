fun main() {
    fun part1(input: List<String>): Int {
        
        return input
            .map { it.toInt() }
            .filterIndexed { index, measurement -> index > 0 && measurement > input[index - 1].toInt() }
            .count()
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    println(part1(testInput))
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println("Part1: ${part1(input)}")
    println("Part2: ${part2(input)}")
}
