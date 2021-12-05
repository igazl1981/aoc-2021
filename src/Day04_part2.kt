fun main() {

    fun part2(input: List<String>): Int {

        val oxygenGeneratorRating = findRating(input) { groupedByBit -> getSizeOfItems(groupedByBit['0']) > getSizeOfItems(groupedByBit['1']) }
        val co2ScrubberRating = findRating(input) { groupedByBit -> getSizeOfItems(groupedByBit['0']) <= getSizeOfItems(groupedByBit['1']) }

        return oxygenGeneratorRating.toInt(2) * co2ScrubberRating.toInt(2)
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")

    val part2Result = part2(testInput)
    println(part2Result)
    check(part2Result == 230) { "Part2 failed: $part2Result" }

    val input = readInput("Day03")

    val part2FinalResult = part2(input)
    println("Part2: $part2FinalResult")
    check(part2FinalResult == 3414905) { "Part 2 final failed: $part2FinalResult" }
}

