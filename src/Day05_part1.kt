fun main() {
    fun part1(input: List<String>): Int {

        val ventLines = input.map { line -> line.let { VentLine.create(line) } }
        val allThePoints = ventLines
            .filter { ventLine -> ventLine.isVertical() || ventLine.isHorizontal() }
            .flatMap { ventLine -> ventLine.getCoveredPoints() }

        val groupingBy = allThePoints
            .groupingBy { it }
        val coveredPoints = groupingBy
            .eachCount()

        val count = coveredPoints
            .count { it.value >= 2 }

//92462 (compareTo)
        return count
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    val part1Result = part1(testInput)
    println(part1Result)
    check(part1Result == 5) { "Part1 failed: $part1Result" }

    val inputPart1 = readInput("Day05")
    val part1FinalResult = part1(inputPart1)
    println("Part1: $part1FinalResult")
    check(part1FinalResult == 4993) { "Part1 Final failed: $part1FinalResult" }
}