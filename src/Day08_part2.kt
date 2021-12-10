fun main() {
    fun part2(digitLines: List<String>): Int {
        return digitLines.sumOf { line ->
            Display(line).getDisplayedNumber()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    val part2Result = part2(testInput)
    println(part2Result)
    check(part2Result == 61229) { "part2 failed: $part2Result" }

    val input = readInput("Day08")
    val part2FinalResult = part2(input)
    println("part2: $part2FinalResult")
    check(part2FinalResult == 998900) { "part2 Final failed: $part2FinalResult" }
}

const val POSITION_ONE = 0
const val POSITION_FOUR = 2
const val POSITION_SEVEN = 1
const val POSITION_EIGHT = 9

class Display(displayLine: String) {

    private var digitDefinitionsSortedByLength: List<List<Char>>
    private var displayedDigits: List<String>

    private val segments: MutableList<Char> = MutableList(7) { ' ' }

    init {
        val displayParts = displayLine.split(" | ").take(2)
        digitDefinitionsSortedByLength = displayParts[0].split(' ').sortedBy { it.length }.map { it.toList() }
        displayedDigits = displayParts[1].split(' ')
        val numbersWithLengthOf5 = digitDefinitionsSortedByLength.filter { it.size == 5 }
        val numbersWithLengthOf6 = digitDefinitionsSortedByLength.filter { it.size == 6 }

        // calculate segment[0]
        segments[0] = calculateSegment0()

        // calculate segments 6
        segments[6] = calculateSegment6(numbersWithLengthOf5)

        // calculate segments 3
        segments[3] = calculateSegment3(numbersWithLengthOf5)


        // calculate segments 1
        segments[1] = calculateSegment1(numbersWithLengthOf6, segments)

        // calculate segments 5
        segments[5] = calculateSegment5(numbersWithLengthOf6)

        // calculate segments 2
        segments[2] = digitDefinitionsSortedByLength[POSITION_ONE].minus(segments).first()

        // calculate segments 4
        segments[4] = digitDefinitionsSortedByLength[POSITION_EIGHT].minus(segments).first()
    }

    private fun calculateSegment5(numbersWithLengthOf6: List<List<Char>>): Char {
        val remainingCharsFrom8MinusSegments = digitDefinitionsSortedByLength[POSITION_EIGHT].minus(segments)
        return numbersWithLengthOf6.fold(remainingCharsFrom8MinusSegments.toSet()) { acc, item ->
            acc.intersect(item)
        }
            .first()
    }

    private fun calculateSegment1(numbersWithLengthOf6: List<List<Char>>, currentSegments: MutableList<Char>): Char {
        val remainingCharsFromNumber8MinusSegments = digitDefinitionsSortedByLength[POSITION_EIGHT]
            .minus(digitDefinitionsSortedByLength[POSITION_ONE])
            .minus(currentSegments)

        return numbersWithLengthOf6
            .fold(remainingCharsFromNumber8MinusSegments.toSet()) { acc, item ->
                acc.intersect(item)
            }.first()
    }

    private fun calculateSegment3(numbersWithLengthOf5: List<List<Char>>): Char {
        val remainingCharsFromNumber4Minus1 = digitDefinitionsSortedByLength[POSITION_FOUR]
            .minus(digitDefinitionsSortedByLength[POSITION_ONE])
        val intersectOfRemainingChars1AndNumbersWithLengthOf5 = numbersWithLengthOf5.fold(remainingCharsFromNumber4Minus1.toSet()) { acc, item ->
            acc.intersect(item)
        }
        return intersectOfRemainingChars1AndNumbersWithLengthOf5.first()
    }

    private fun calculateSegment6(numbersWithLengthOf5: List<List<Char>>): Char {
        val remainingCharsFromNumber8And7And4 = digitDefinitionsSortedByLength[9]
            .minus(digitDefinitionsSortedByLength[POSITION_SEVEN])
            .minus(digitDefinitionsSortedByLength[POSITION_FOUR])
        return numbersWithLengthOf5
            .fold(remainingCharsFromNumber8And7And4.toSet()) { acc, item ->
                acc.intersect(item)
            }.first()
    }

    private fun calculateSegment0() = digitDefinitionsSortedByLength[POSITION_SEVEN]
        .minus(digitDefinitionsSortedByLength[POSITION_ONE])
        .first()

    fun getDisplayedNumber(): Int {
        return displayedDigits.map { digitText -> getDisplayedValue(digitText) }
            .joinToString("")
            .toInt()
    }

    private fun getDisplayedValue(digitText: String): Int {
        return digitText
            .map { segmentCharacter -> segments.indexOf(segmentCharacter) }
            .let { activeSegments -> Digit.getDisplayedValueByActiveSegments(activeSegments) }
    }


    enum class Digit(val activeSegments: List<Int>, val displayedValue: Int) {
        ZERO(listOf(0, 1, 2, 4, 5, 6), 0),
        ONE(listOf(2, 5), 1),
        TWO(listOf(0, 2, 3, 4, 6), 2),
        THREE(listOf(0, 2, 3, 5, 6), 3),
        FOUR(listOf(1, 2, 3, 5), 4),
        FIVE(listOf(0, 1, 3, 5, 6), 5),
        SIX(listOf(0, 1, 3, 4, 5, 6), 6),
        SEVEN(listOf(0, 2, 5), 7),
        EIGHT(listOf(0, 1, 2, 3, 4, 5, 6), 8),
        NINE(listOf(0, 1, 2, 3, 5, 6), 9);

        companion object {
            fun getDisplayedValueByActiveSegments(segments: List<Int>) = values()
                .first { digit ->
                    (digit.activeSegments.size == segments.size)
                            && (digit.activeSegments.containsAll(segments) && segments.containsAll(digit.activeSegments))
                }
                .displayedValue
        }

    }


}