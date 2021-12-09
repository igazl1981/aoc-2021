var verticalSize: Int = 0
var horizontalSize: Int = 0

fun main() {
    fun part2(input: List<List<Int>>): Int {
        verticalSize = input.size
        horizontalSize = input[0].size


        val basinSizes = mutableListOf<Int>()
        for (row in 0 until verticalSize) {
            for (column in 0 until horizontalSize) {
                val element = getBasinSize(row, column, checkedCoordinates = mutableListOf(), inputX = input).size
                basinSizes.add(element)
            }
        }




        return basinSizes.sorted().takeLast(3).reduce { acc, i -> if (acc == 0) i else acc * i }

    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test").map { line -> line.map { it.toString().toInt() } }
    val part2Result = part2(testInput)
    println(part2Result)
    check(part2Result == 1134) { "Part2 failed: $part2Result" }

    val input = readInput("Day09").map { line -> line.map { it.toString().toInt() } }
    println("Part2: ${part2(input)}")
}


fun getBasinSize(row: Int, column: Int, previousValue: Int = -1, checkedCoordinates: MutableList<Pair<Int, Int>> = mutableListOf(), inputX: List<List<Int>>): MutableList<Pair<Int, Int>> {
    if (inputX[row][column] == 9 || previousValue >= inputX[row][column] || checkedCoordinates.contains(Pair(row, column))) {
        return checkedCoordinates
    }
    var newCoordinates = checkedCoordinates.plus(Pair(row, column)).toMutableList()

    if (row > 0) {
        newCoordinates = getBasinSize(row - 1, column, inputX[row][column], newCoordinates, inputX)
    }
    if (row < verticalSize - 1) {
        newCoordinates = getBasinSize(row + 1, column, inputX[row][column], newCoordinates, inputX)
    }
    if (column > 0) {
        newCoordinates = getBasinSize(row, column - 1, inputX[row][column], newCoordinates, inputX)
    }
    if (column < horizontalSize - 1) {
        newCoordinates = getBasinSize(row, column + 1, inputX[row][column], newCoordinates, inputX)
    }

    return newCoordinates
}
