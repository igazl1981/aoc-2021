fun main() {
    fun part1(input: List<Pair<Direction, Int>>): Int {
        return input
            .fold(Position()) { acc, pair ->
                when (pair.first) {
                    Direction.FORWARD -> acc.copy(horizontal = acc.horizontal + pair.second)
                    Direction.UP -> acc.copy(vertical = acc.vertical - pair.second)
                    Direction.DOWN -> acc.copy(vertical = acc.vertical + pair.second)
                }
            }
            .let { finalPosition -> finalPosition.horizontal * finalPosition.vertical }
    }

    fun part2(input: List<Pair<Direction, Int>>): Int {
        return input
            .fold(Position()) { acc, pair ->
                when (pair.first) {
                    Direction.FORWARD -> acc.copy(
                            horizontal = acc.horizontal + pair.second,
                            vertical = acc.vertical + (acc.aim * pair.second)
                        )
                    Direction.UP -> acc.copy(aim = acc.aim - pair.second)
                    Direction.DOWN -> acc.copy(aim = acc.aim + pair.second)
                }
            }
            .let { finalPosition -> finalPosition.horizontal * finalPosition.vertical }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInputForDay2("Day02_test")
    val part1Result = part1(testInput)
    println(part1Result)
    check(part1Result == 150) { "Part1 failed: $part1Result" }
    val part2Result = part2(testInput)
    println(part2Result)
    check(part2Result == 900) { "Part2 failed: $part2Result" }

    val input = readInputForDay2("Day02")
    println("Part1: ${part1(input)}")
    println("Part2: ${part2(input)}")
}

enum class Direction {
    DOWN, FORWARD, UP;

    companion object {
        fun fromString(source: String) = valueOf(source.uppercase())
    }
}

data class Position(
    val horizontal: Int = 0,
    val vertical: Int = 0,
    val aim: Int = 0
)

//enum class Position {
//    HORIZONTAL, VERTICAL, AIM
//}