fun main() {
    fun part1(input: List<List<Int>>): Int {

        val windowed = input
            .windowed(3, 1)
        var sumOfLowestRiskLevel = 0
        windowed
            .asSequence()
            .forEachIndexed { windowsIndex, window ->
                // if windowsIndex == 0
                //check 1st and middle row
                // windowsIndex last check middle and last row
                if (windowsIndex == 0) {
                    // first window check 0 row and middle row
                    val sumOfLowestRiskLevelInFirstRow = getFirstRowLowestRiskLevelSummary(window)
                    val sumOfLowestRiskLevelInMiddleRow = getMiddleRowLowestRiskLevelSummary(window)
                    sumOfLowestRiskLevel += sumOfLowestRiskLevelInFirstRow + sumOfLowestRiskLevelInMiddleRow
                } else if (windowsIndex == windowed.lastIndex) {
                    val sumOfLowestRiskLevelLastFirstRow = getLastRowLowestRiskLevelSummary(window)
                    val sumOfLowestRiskLevelInMiddleRow = getMiddleRowLowestRiskLevelSummary(window)
                    sumOfLowestRiskLevel += sumOfLowestRiskLevelLastFirstRow + sumOfLowestRiskLevelInMiddleRow
                } else {
                    val sumOfLowestRiskLevelInMiddleRow = getMiddleRowLowestRiskLevelSummary(window)
                    sumOfLowestRiskLevel += sumOfLowestRiskLevelInMiddleRow
                }
            }

        return sumOfLowestRiskLevel
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test").map { line -> line.map { it.toString().toInt() } }
    val part1Result = part1(testInput)
    println(part1Result)
    check(part1Result == 15) { "Part1 failed: $part1Result" }


    val input = readInput("Day09").map { line -> line.map { it.toString().toInt() } }
    val part1FinalResult = part1(input)
    println("Part1: $part1FinalResult")
    check(part1FinalResult == 562) { "Part1 failed: $part1FinalResult" }
}

private fun getMiddleRowLowestRiskLevelSummary(window: List<List<Int>>) = window[1].mapIndexed { i, value ->
    if (i == 0) {
        if (value < window[1][1] && value < window[0][0] && value < window[2][0]) {
            value + 1
        } else {
            0
        }
    } else if (i == window[1].lastIndex) {
        if (value < window[1][i - 1] && value < window[0][i] && value < window[2][i]) {
            value + 1
        } else {
            0
        }
    } else {
        if (value < window[1][i - 1]
            && value < window[1][i + 1]
            && value < window[0][i]
            && value < window[2][i]
        ) {
            value + 1
        } else {
            0
        }
    }
}.sum()

private fun getFirstRowLowestRiskLevelSummary(window: List<List<Int>>) = window[0].mapIndexed { rowIndex, value ->
    if (rowIndex == 0) {
        if (value < window[0][1] && value < window[1][0]) {
            value + 1
        } else {
            0
        }
    } else if (rowIndex == window[0].lastIndex) {
        if (window[0][rowIndex] < window[0][rowIndex - 1] && window[0][rowIndex] < window[1][rowIndex]) {
            window[0][rowIndex] + 1
        } else {
            0
        }
    } else {
        if (window[0][rowIndex] < window[0][rowIndex - 1]
            && window[0][rowIndex] < window[0][rowIndex + 1]
            && window[0][rowIndex] < window[1][rowIndex]
        ) {
            window[0][rowIndex] + 1
        } else {
            0
        }
    }
}.sum()

fun getLastRowLowestRiskLevelSummary(window: List<List<Int>>) = window[2].mapIndexed { rowIndex, value ->
    if (rowIndex == 0) {
        if (value < window[2][1] && value < window[1][0]) {
            value + 1
        } else {
            0
        }
    } else if (rowIndex == window[2].lastIndex) {
        if (value < window[2][rowIndex - 1] && value < window[1][rowIndex]) {
            value + 1
        } else {
            0
        }
    } else {
        if (value < window[2][rowIndex - 1]
            && value < window[2][rowIndex + 1]
            && value < window[1][rowIndex]
        ) {
            value + 1
        } else {
            0
        }
    }
}.sum()

