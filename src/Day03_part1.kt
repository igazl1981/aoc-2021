
fun main() {
    fun part1(input: List<String>): Int {
        val gamma = mutableListOf<Int>()
        input
            .map { line ->
                if (gamma.isEmpty()) {
                    for (i in line.indices) {
                        gamma.add(0)
                    }
                }
                return@map line.forEachIndexed { index, character ->
                    if (character == '1') {
                        gamma[index] = gamma[index] + 1
                    }
                }
            }
        val gammaBinary = gamma.map { if (it > input.size / 2) 1 else 0 }.joinToString("")
        val epsilonBinary = gamma.map { if (it < input.size / 2) 1 else 0 }.joinToString("")
        val gammaDecimal = Integer.parseInt(gammaBinary, 2)
        val epsilonDecimal = Integer.parseInt(epsilonBinary, 2)
        println("joined: $gammaBinary")
        println("gammaD: ${gammaDecimal}")
        println("epsilonD: ${epsilonDecimal}")
        println("gamma: $gamma")
        return gammaDecimal * epsilonDecimal
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    val part1Result = part1(testInput)
    println(part1Result)
    check(part1Result == 198) { "Part1 failed: $part1Result" }

    val inputPart1 = readInput("Day03")
    println("Part1: ${part1(inputPart1)}")
}