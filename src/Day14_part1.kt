fun main() {

    fun part1(input: List<String>): Int {
        val template = input[0]
        val insertionRules = input.drop(2)
            .map { line -> line.split(" -> ") }
            .associate { lineParts -> lineParts[0] to lineParts[1] }
        val finalPolymer = (0 until 10)
            .fold(template) { polymer, _ ->
                val newPolymer = polymer.windowed(2, 1)
                    .mapIndexed { index, insertionKey ->
                        val insertion = insertionRules[insertionKey]!!
                        if (index > 0) {
                            "$insertion${insertionKey[1]}"
                        } else {
                            "${insertionKey[0]}$insertion${insertionKey[1]}"
                        }
                    }
                    .joinToString("")
                newPolymer
            }

        val characterCountInPolymer = finalPolymer
            .groupingBy { it }
            .eachCount()

        val mostCommonElementCounter = characterCountInPolymer.maxOf { it.value }
        val leastCommonElementCounter = characterCountInPolymer.minOf { it.value }

        return mostCommonElementCounter - leastCommonElementCounter
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day14_test")
    val part1Result = part1(testInput)
    println(part1Result)
    check(part1Result == 1588) { "Part1 failed: $part1Result" }

    val input = readInput("Day14")
    val part1FinalResult = part1(input)
    println("Part1: $part1FinalResult")
    check(part1FinalResult == 2010) { "Part1  Final failed: $part1FinalResult" }
}