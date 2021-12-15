data class Instruction(val direction: String, val amount: Int)
data class Coordinate(val x: Int, val y: Int)

fun main() {

    fun printPaper(visibleDotCoordinates: MutableList<Coordinate>, maximumCoordinates: Coordinate) {
        val remainingDots = visibleDotCoordinates
            .filter { coordinate -> coordinate.x <= maximumCoordinates.x && coordinate.y <= maximumCoordinates.y }
        for (y in 0..maximumCoordinates.y) {
            for (x in 0..maximumCoordinates.x) {
                val found = remainingDots.firstOrNull { it.x == x && it.y == y } != null
                if (found) {
                    print("#")
                } else {
                    print(".")
                }
            }
            println()
        }

        println()
    }

    fun part1(input: List<String>): Int {

        val visibleDotCoordinates = input
            .takeWhile { it.isNotEmpty() }
            .map { lines -> lines.split(",").map { it.toInt() } }
            .map { Coordinate(it[0], it[1]) }
            .toMutableList()

        var maximumCoordinates = visibleDotCoordinates
            .fold(Coordinate(0, 0)) { acc, coordinate ->

                val with = maxOf(coordinate.x, acc.x)
                val height = maxOf(coordinate.y, acc.y)
                Coordinate(with, height)
            }

        val foldingInstructions = input.drop(visibleDotCoordinates.size + 1)
            .map {
                val regexResult = Regex("fold along (.)=([0-9]+)").matchEntire(it)!!
                Instruction(
                    regexResult.groups[1]!!.value,
                    regexResult.groups[2]!!.value.toInt()
                )
            }


        for (instruction in foldingInstructions) {
            if (instruction.direction == "y") {
                visibleDotCoordinates
                    .filter { coordinate ->
                        coordinate.x <= maximumCoordinates.x
                                && coordinate.y <= maximumCoordinates.y
                                && coordinate.y > instruction.amount
                    }
                    .forEach { coordinate ->
                        // visible which is inside the paper
                        //  and under folding
                        val distanceY = coordinate.y - instruction.amount //will +
                        // check if matching pair above folding exists
                        val checkingCoordinate = Coordinate(coordinate.x, instruction.amount - distanceY)
                        val missingVisibleMatchingPairAboveFold = visibleDotCoordinates
                            .none { coordinateAboveFold -> coordinateAboveFold == checkingCoordinate }

                        if (missingVisibleMatchingPairAboveFold) {
                            visibleDotCoordinates.add(checkingCoordinate)
                        }
                    }

                maximumCoordinates = maximumCoordinates.copy(y = instruction.amount - 1)
            } else if (instruction.direction == "x") {
                visibleDotCoordinates
                    .filter { coordinate ->
                        coordinate.x <= maximumCoordinates.x
                                && coordinate.y <= maximumCoordinates.y
                                && coordinate.x > instruction.amount
                    }
                    .forEach { coordinate ->
                        // visible which is inside the paper
                        //  and under folding
                        val distanceX = coordinate.x - instruction.amount //will +
                        // check if matching pair above folding exists
                        val checkingCoordinate = Coordinate(instruction.amount - distanceX, coordinate.y)
                        val missingVisibleMatchingPairAboveFold = visibleDotCoordinates
                            .none { coordinateAboveFold -> coordinateAboveFold == checkingCoordinate }

                        if (missingVisibleMatchingPairAboveFold) {
                            visibleDotCoordinates.add(checkingCoordinate)
                        }
                    }
                maximumCoordinates = maximumCoordinates.copy(x = instruction.amount - 1)
            }


            printPaper(visibleDotCoordinates, maximumCoordinates)
            break
        }




        return visibleDotCoordinates
            .count { coordinate -> coordinate.x <= maximumCoordinates.x && coordinate.y <= maximumCoordinates.y }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day13_test")
    val part1Result = part1(testInput)
    println(part1Result)
    check(part1Result == 17) { "Part1 failed: $part1Result" }

    val input = readInput("Day13")
    val part1FinalResult = part1(input)
    println("Part1: $part1FinalResult")
    check(part1FinalResult == 1691) { "Part1  Final failed: $part1FinalResult" }
}