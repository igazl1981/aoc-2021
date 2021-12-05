fun main() {
    fun part1(input: List<String>): Int {

        val chosenNumbers = input[0].split(",").map { it.toInt() }

        val boards = input.drop(2).get5by5Matrices()
        var result = 0
        chosenNumbers.asSequence().takeWhile { result == 0 }
            .fold(boards) { newBoards, currentNumber ->
                val acc = newBoards
                    .map { board -> board.getMatrixWithoutChosenNumber(currentNumber) }
                    .also { boardsWithMarkedNumbers ->
                        boardsWithMarkedNumbers
                            .find { board -> board.hasBingoLineOrColumn() }
                            ?.also { boardWithBingo ->
                                result = currentNumber * boardWithBingo.sumOfRemainingNumbers() }
                    }
                acc
            }

        return result
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    val part1Result = part1(testInput)
    println(part1Result)
    check(part1Result == 4512) { "Part1 failed: $part1Result" }

    val inputPart1 = readInput("Day04")
    val part1FinalResult = part1(inputPart1)
    println("Part1: $part1FinalResult")
    check(part1FinalResult == 89001) { "Part1 Final failed: $part1FinalResult" }
}

/**
 * Returns 5x5 Matrices.
 * The input has to be lines with numbers devided by space(s)
 * Every matrix representation is separated by an empty line
 */
internal fun List<String>.get5by5Matrices() = this.windowed(5, 6)
    .map { boardLines ->
        val rows = boardLines
            .map { boardLine ->
                boardLine.trim()
                    .split("[ ]+".toRegex())
                    .map {
                        it.toInt()
                    }
            }
        Matrix(rows)
    }