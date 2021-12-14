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

    fun part2(octopusEnergyMap: List<List<Int>>): Int {
        val energyCalculationMap = octopusEnergyMap.map { it.toMutableList() }.toMutableList()
        var stepCounter = 0;


        while(!energyCalculationMap.all { row -> row.all { it == 0 } }) {

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
            stepCounter++
        }


        return stepCounter
    }

// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test").map { it.map(Character::getNumericValue) }
    val part2Result = part2(testInput)
    println(part2Result)
    check(part2Result == 195) { "Part2 failed: $part2Result" }

    val input = readInput("Day11").map { it.map(Character::getNumericValue) }
    val part2FinalResult = part2(input)
    println("Part2: $part2FinalResult")
    check(part2FinalResult == 1) { "Part2 Final failed: $part2Result" }

}
