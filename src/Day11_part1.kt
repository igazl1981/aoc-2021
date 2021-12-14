private const val width = 10
private const val height = 10

fun main() {

    fun flashAdjacent(map:  MutableList<MutableList<Int>>, row: Int, column: Int) {
        for (r in -1..1) {
            for (c in -1..1) {
                if (r != 0 || c != 0) {
                    val newRow = row + r
                    val newColumn = column + c
                    if (newRow >= 0 && newColumn >= 0 && newRow < 10 && newColumn < 10) {
                        if (map[newRow][newColumn] > 0)
                            map[newRow][newColumn]++
                    }
                }
            }
        }
    }

    fun part1(octopusEnergyMap: List<List<Int>>): Int {
        val energyCalculationMap = octopusEnergyMap.map { it.toMutableList() }.toMutableList()
        var flashCounter = 0;


        for (step in 0 until 100) {

            // increase energy
            energyCalculationMap.forEachIndexed { row, columns ->
                columns.forEachIndexed { column, _ ->
                    energyCalculationMap[row][column]++
                }
            }

            while (energyCalculationMap.firstOrNull { columns -> columns.firstOrNull { octopus -> octopus > 9 } != null } != null) {
                energyCalculationMap.forEachIndexed { row, columns ->
                    columns.forEachIndexed { column, octopus ->
                        if (octopus > 9) {
                            flashAdjacent(energyCalculationMap, row, column)
                            energyCalculationMap[row][column] = 0
                        }
                    }
                }
            }
            val c = energyCalculationMap.sumOf { row -> row.count { it == 0 } }
            flashCounter += c
        }


//        (0 until 100).forEach { step ->
//            val flashedOctopuses = ArrayDeque<Pair<Int, Int>>()
//            (0 until height).forEach { row ->
//                (0 until width).forEach { column ->
//                    energyCalculationMap[row][column]++
//                    if (energyCalculationMap[row][column] > 9) {
//                        flashedOctopuses.addLast(row to column)
//                        energyCalculationMap[row][column] = 0
//                    }
//                }
//            }
//            while (flashedOctopuses.isNotEmpty()) {
//                val (flashRow, flashColumn) = flashedOctopuses.removeFirst()
//                (-1..1).forEach { rowToCheck ->
//                    (-1..1).forEach { columnToCheck ->
//                        if (rowToCheck != 0 || columnToCheck != 0) {
//                            val currentRow = flashRow + rowToCheck
//                            val currentColumn = flashColumn + columnToCheck
//                            if (
//                                currentRow in 0 until height
//                                && currentColumn in 0 until width
//                            ) {
//                                if (energyCalculationMap[currentRow][currentColumn] > 0) {
//                                    energyCalculationMap[currentRow][currentColumn]++
//                                }
//                                if (energyCalculationMap[currentRow][currentColumn] > 9) {
//                                    flashedOctopuses.addFirst(currentRow to currentColumn)
//                                    energyCalculationMap[currentRow][currentColumn] = 0
//                                }
//                            }
//                        }
//
//                    }
//                }
//            }
//
//            var c = 0
//            energyCalculationMap.forEach {
//                println(it)
//                c += it.count { it==0 }
//            }
//
//            println()
//            println()
//            println()
//            println()
//            println()
//            flashCounter += c
//        }

        return flashCounter
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test").map { it.map(Character::getNumericValue) }
    val part1Result = part1(testInput)
    println(part1Result)
    check(part1Result == 1656) { "Part1 failed: $part1Result" }


    val input = readInput("Day11").map { it.map(Character::getNumericValue) }
    val part1FinalResult = part1(input)
    println("Part1: $part1FinalResult")
    check(part1FinalResult == 1691) { "Part1  Final failed: $part1FinalResult" }
}