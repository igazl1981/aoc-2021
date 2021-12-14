fun main() {
    fun part2(input: List<String>): Long {

        return 1
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    val part2Result = part2(testInput)
    println(part2Result)
    check(part2Result == 288957L) { "Part2 failed: $part2Result" }

    val input = readInput("Day10")
    val part2FinalResult = part2(input)
    println("Part2: $part2FinalResult")
    check(part2FinalResult == 1685293086L) { "Part2 failed: $part2Result" }

}
