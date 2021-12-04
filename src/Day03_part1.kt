fun main() {
    fun part1(input: List<String>): Int {
        val binaryLength = input.first().length
        val gammaBinaryString = (0 until binaryLength)
            .fold("") { gammaX, indexOfBit ->
                val entryOfMostCommonBit = input.groupingBy { line -> line[indexOfBit] }
                    .eachCount()
                    .maxWithOrNull { a, b -> if (a.value < b.value) -1 else 1 }
                gammaX + entryOfMostCommonBit?.key
            }

        val epsilonBinaryString = gammaBinaryString.map { if (it == '0') '1' else '0' }.joinToString("")
        val gammaDecimal = Integer.parseInt(gammaBinaryString, 2)
        val epsilonDecimal = Integer.parseInt(epsilonBinaryString, 2)
        println("gammaString: $gammaBinaryString")
        println("epsilonString: $epsilonBinaryString")
        println("gammaD: $gammaDecimal")
        println("epsilonD: $epsilonDecimal")

        return gammaDecimal * epsilonDecimal
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    val part1Result = part1(testInput)
    println(part1Result)
    check(part1Result == 198) { "Part1 failed: $part1Result" }

    val inputPart1 = readInput("Day03")
    val part1FinalResult = part1(inputPart1)
    println("Part1: $part1FinalResult")
    check(part1FinalResult == 4191876) { "Part1 Final failed: $part1FinalResult" }
}