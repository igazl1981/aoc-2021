data class Matrix(val rows: List<List<Int>>) {

    fun hasBingoLineOrColumn() = hasBingoLine() || hasBingoColumn()

    private fun hasBingoLine() = rows.firstOrNull { line -> line.none { numberInRow -> numberInRow >= 0 } } != null

    private fun hasBingoColumn() = transpose().firstOrNull { line -> line.none { numberInRow -> numberInRow >= 0 } } != null

    fun getMatrixWithoutChosenNumber(chosenNumber: Int) = Matrix(getRowsWithoutChosenNumber(chosenNumber))

    fun sumOfRemainingNumbers(): Int {
        return rows.sumOf { line -> line.filter { it >= 0 }.sum() }
    }

    private fun transpose() = rows[0].mapIndexed { column, _ -> rows.mapIndexed { row, _ -> rows[row][column] } }

    private fun getRowsWithoutChosenNumber(chosenNumber: Int) = rows.map { line -> line.map { if (it == chosenNumber) -1 else it } }
}

data class VentLine(val startPoint: Point, val endPoint: Point) {

    fun isVertical() = startPoint.x == endPoint.x

    fun isHorizontal() = startPoint.y == endPoint.y

    fun getCoveredPoints(): MutableList<Point> {
        val points = mutableListOf<Point>()


        val directionX = if (startPoint.x > endPoint.x) -1 else if (startPoint.x < endPoint.x) 1 else 0
        val directionY = if (startPoint.y > endPoint.y) -1 else if (startPoint.y < endPoint.y) 1 else 0
        var x = 0
        var y = 0

        do {
            val newX = startPoint.x + (x * directionX)
            val newY = startPoint.y + (x * directionY)
            x++
            y++
            val current = Point(newX, newY)
            points.add(current)
        } while (current != endPoint)

//        for (point in startPoint..endPoint) {
//            points.add(point)
//        }
        return points
    }

    companion object {
        fun create(input: String): VentLine {
            val points = input
                .split(" -> ")
                .map { it.split(',').map(String::toInt) }
                .map { coordinates -> Point(coordinates[0], coordinates[1]) }
            return VentLine(points[0], points[1])
        }
    }
}

data class Point(val x: Int, val y: Int) : Comparable<Point> {
    override fun compareTo(other: Point): Int {
        return 0
    }

    operator fun rangeTo(that: Point) = PointRange(this, that)
}

class PointIterator(startPoint: Point, private val endPoint: Point) : Iterator<Point> {
    private val initial = startPoint
    private var current = startPoint

    private val directionX = if (startPoint.x > endPoint.x) -1 else if (startPoint.x < endPoint.x) 1 else 0
    private val directionY = if (startPoint.y > endPoint.y) -1 else if (startPoint.y < endPoint.y) 1 else 0
    private var x = 0
    private var y = 0

    override fun hasNext(): Boolean {
        return current != endPoint
    }

    override fun next(): Point {
        val newX = initial.x + (x * directionX)
        val newY = initial.y + (x * directionY)
        x++
        y++
        current = Point(newX, newY)
        return current
    }
}

class PointRange(override val start: Point, override val endInclusive: Point) : ClosedRange<Point>, Iterable<Point> {
    override fun iterator(): Iterator<Point> {
        return PointIterator(start, endInclusive)
    }
}
