fun main() {
    fun part2(input: List<String>): Int {
        val ventLines = input.map { line -> line.let { VentLine.create(line) } }

        return ventLines
//            .filter { ventLine -> ventLine.isVertical() || ventLine.isHorizontal() }
            .flatMap { ventLine -> ventLine.getCoveredPoints() }
            .groupingBy { it }
            .eachCount()
            .count { it.value >= 2 }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    val part2Result = part2(testInput)
    println(part2Result)
    check(part2Result == 12) { "part2 failed: $part2Result" }

    val inputpart2 = readInput("Day05")
    val part2FinalResult = part2(inputpart2)
    println("part2: $part2FinalResult")
    check(part2FinalResult == 21101) { "part2 Final failed: $part2FinalResult" }
}