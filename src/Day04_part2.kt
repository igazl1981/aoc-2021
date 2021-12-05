fun main() {
    fun part2(input: List<String>): Int {

        val chosenNumbers = input[0].split(",").map { it.toInt() }

        val boards = input.drop(2).get5by5Matrices()
        var result = 0
        chosenNumbers.asSequence().takeWhile { result == 0 }
            .fold(boards) { newBoards, currentNumber ->

                val acc = newBoards
                    .map { board -> board.getMatrixWithoutChosenNumber(currentNumber) }
                    .filter { board -> !board.hasBingoLineOrColumn() }
                if (newBoards.size == 1 && acc.isEmpty()) {
                    result = currentNumber * newBoards[0].getMatrixWithoutChosenNumber(currentNumber).sumOfRemainingNumbers()
                }
                acc
            }

        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    val part2Result = part2(testInput)
    println(part2Result)
    check(part2Result == 1924) { "part2 failed: $part2Result" }

    val inputPart2 = readInput("Day04")
    val part2FinalResult = part2(inputPart2)
    println("part2: $part2FinalResult")
    check(part2FinalResult == 89001) { "part2 Final failed: $part2FinalResult" }
}
