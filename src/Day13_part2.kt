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

    fun part2(input: List<String>): Int {

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
        }




        return visibleDotCoordinates
            .count { coordinate -> coordinate.x <= maximumCoordinates.x && coordinate.y <= maximumCoordinates.y }
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day13_test")
    val part2Result = part2(testInput)
    println(part2Result)
    check(part2Result == 16) { "Part2 failed: $part2Result" }

    val input = readInput("Day13")
    val part2FinalResult = part2(input)
    println("Part2: $part2FinalResult")
    check(part2FinalResult == 152837) { "Part2 Final failed: $part2Result" }
}