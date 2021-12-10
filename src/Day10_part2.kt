fun main() {
    fun part2(input: List<String>): Long {

        val bracketValues = mapOf(
            ')' to 1,
            ']' to 2,
            '}' to 3,
            '>' to 4
        )

        val sumOfRemainingClosingBrackets = input
            .asSequence()
            .map(String::toList)
            .map { charactersListInLine -> findOpenBracketsWithoutClosing(charactersListInLine) }
            .filter { it.second }
            .map { it.first }
            .map { remainingOpenBrackets ->
                remainingOpenBrackets
                    .reversed()
                    .fold(0L) { result, openingBracketCharacter ->
                        val closingBracketCharacter = Bracket.getClosingBracketCharacter(openingBracketCharacter)
                        val pointOfClosingBracket = bracketValues.getOrDefault(closingBracketCharacter, 0)
                        (result * 5) + pointOfClosingBracket
                    }
            }
            .sorted()
            .toList()

        return sumOfRemainingClosingBrackets[sumOfRemainingClosingBrackets.size / 2]

    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    val part2Result = part2(testInput)
    println(part2Result)
    check(part2Result == 288957L) { "Part2 failed: $part2Result" }

    val input = readInput("Day10")
    val part2FinalResult = part2(input)
    println("Part2: $part2FinalResult")
    check(part2FinalResult == 1685293086L) { "Part2 failed: $part2Result" }

}

private fun findOpenBracketsWithoutClosing(charactersInLine: List<Char>): Pair<ArrayDeque<Char>, Boolean> {
    var notFoundIllegalClosingBracket = true
    var index = 0
    val stack = ArrayDeque<Char>()
    while (notFoundIllegalClosingBracket && index < charactersInLine.size) {
        if (Bracket.OPEN.characters.contains(charactersInLine[index])) {
            stack.addLast(charactersInLine[index])
            index++
        } else {
            val lastOpenCaret = stack.removeLast()
            if (Bracket.areTheyPair(lastOpenCaret, charactersInLine[index])) {
                index++
            } else {
                notFoundIllegalClosingBracket = false
            }
        }
    }
    return stack to notFoundIllegalClosingBracket
}