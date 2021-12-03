fun main() {
    fun part1(input: List<Pair<Direction, Int>>): Int {
        val initialMap = mutableMapOf(Position.HORIZONTAL to 0, Position.VERTICAL to 0)
        val fold = input
            .fold(initialMap) { acc, pair ->
                when (pair.first) {
                    Direction.FORWARD -> {
                        acc[Position.HORIZONTAL] = acc[Position.HORIZONTAL]!! + pair.second
                        acc
                    }
                    Direction.UP -> {
                        acc[Position.VERTICAL] = acc[Position.VERTICAL]!! - pair.second
                        acc
                    }
                    Direction.DOWN -> {
                        acc[Position.VERTICAL] = acc[Position.VERTICAL]!! + pair.second
                        acc
                    }
                }
            }

        return fold[Position.HORIZONTAL]!! * fold[Position.VERTICAL]!!
    }

    fun part2(input: List<Pair<Direction, Int>>): Int {

        val initialMap = mutableMapOf(Position.HORIZONTAL to 0, Position.VERTICAL to 0, Position.AIM to 0)
        val fold = input
            .fold(initialMap) { acc, pair ->
                when (pair.first) {
                    Direction.FORWARD -> {
                        acc[Position.HORIZONTAL] = acc[Position.HORIZONTAL]!! + pair.second
                        acc[Position.VERTICAL] = acc[Position.VERTICAL]!! + (acc[Position.AIM]!! * pair.second)
                        acc
                    }
                    Direction.UP -> {
                        acc[Position.AIM] = acc[Position.AIM]!! - pair.second
                        acc
                    }
                    Direction.DOWN -> {
                        acc[Position.AIM] = acc[Position.AIM]!! + pair.second
                        acc
                    }
                }
            }
        return fold[Position.HORIZONTAL]!! * fold[Position.VERTICAL]!!
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

enum class Position {
    HORIZONTAL, VERTICAL, AIM
}