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
