fun main() {

    fun part2(input: List<String>): Long {
        val template = input[0]
        val insertionRules = input.drop(2)
            .map { line -> line.split(" -> ") }
            .associate { lineParts -> lineParts[0] to lineParts[1][0] }
        val insertionKeysWithCount = template.windowed(2, 1)
            .groupingBy { it }
            .eachCount()
            .map { it.key to it.value.toLong() }
            .toMap()

        val insertionCounter = template
            .groupingBy { it }
            .eachCount()
            .mapValues { entry -> entry.value.toLong() }
            .toMutableMap()

        val finalInsertKeysWithCount = (0..39)
            .fold(insertionKeysWithCount) { acc, i ->
                val newInsertionKeys = mutableMapOf<String, Long>()
                acc.forEach {entry ->
                    val insertion = insertionRules[entry.key]!!
                    insertionCounter[insertion] = insertionCounter.getOrDefault(insertion, 0) + entry.value
                    val ins1 = "${entry.key[0]}$insertion"
                    val ins2 = "$insertion${entry.key[1]}"
                    newInsertionKeys[ins1] = newInsertionKeys.getOrDefault(ins1, 0) + entry.value
                    newInsertionKeys[ins2] = newInsertionKeys.getOrDefault(ins2, 0) + entry.value
                }
                newInsertionKeys
            }

        val mostCommonEntry = insertionCounter.maxByOrNull { it.value }!!
        val leastCommonEntry = insertionCounter.minByOrNull { it.value }!!

        return  mostCommonEntry.value - leastCommonEntry.value
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day14_test")
    val part2Result = part2(testInput)
    println(part2Result)
    check(part2Result == 2188189693529) { "Part2 failed: $part2Result" }

    val input = readInput("Day14")
    val part2FinalResult = part2(input)
    println("Part2: $part2FinalResult")
    check(part2FinalResult == 1L) { "Part2 Final failed: $part2FinalResult" }
}