fun main() {

    fun part1(input: List<String>): Int {

        val bracketValues = mapOf(
            ')' to 3,
            ']' to 57,
            '}' to 1197,
            '>' to 25137
        )


        val pointsOfIllegalClosingBrackets = input
            .map(String::toList)
            .sumOf { charactersListInLine ->
                val illegalClosingBracket = findIllegalClosingBracket(charactersListInLine)
                    ?.let { charactersListInLine[it] } ?: ' '
                bracketValues.getOrDefault(illegalClosingBracket, 0)
            }


        return pointsOfIllegalClosingBrackets
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    val part1Result = part1(testInput)
    println(part1Result)
    check(part1Result == 26397) { "Part1 failed: $part1Result" }


    val input = readInput("Day10")
    val part1FinalResult = part1(input)
    println("Part1: $part1FinalResult")
    check(part1FinalResult == 271245) { "Part1 failed: $part1FinalResult" }
}


enum class Bracket(val characters: List<Char>) {
    OPEN(listOf('(', '[', '{', '<')),
    CLOSE(listOf(')', ']', '}', '>'));

    companion object {
        fun areTheyPair(openingBracket: Char, closingBracket: Char) = OPEN.characters.indexOf(openingBracket) == CLOSE.characters.indexOf(closingBracket)
    }
}

fun findIllegalClosingBracket(charactersInLine: List<Char>): Int? {
    var notFound = true
    var index = 0
    val stack = ArrayDeque<Int>()
    while (notFound && index < charactersInLine.size) {
        if (Bracket.OPEN.characters.contains(charactersInLine[index])) {
            stack.addLast(index)
            index++
        } else {
            val lastOpenCaret = stack.removeLast()
            if (Bracket.areTheyPair(charactersInLine[lastOpenCaret], charactersInLine[index])) {
                index++
            } else {
                notFound = false
            }
        }
    }
    return if (notFound) null else index
}
