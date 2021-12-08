fun main() {
    fun part1(digitLines: List<String>): Int {
        val digitParts = digitLines
            .map { line -> line.split(" | ") }
            .map { it[1].split(" ") }

        return digitParts
            .flatten()
            .count { listOf(2, 3, 4, 7).contains(it.length) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")

    val part1Result = part1(testInput)
    println(part1Result)
    check(part1Result == 26) { "Part1 failed: $part1Result" }

    val input = readInput("Day08")
    val part1FinalResult = part1(input)
    println("Part1: $part1FinalResult")
    check(part1FinalResult == 383) { "Part1 Final failed: $part1FinalResult" }
}

