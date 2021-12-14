
private val allPaths = mutableListOf<List<String>>()

fun main() {

    fun part2(input: List<String>): Int {
        allPaths.removeAll { true }
        val connectionMap = createConnectionMap(input)
        findPath2("start", mutableListOf(), connectionMap)
        return allPaths.size
    }

// test if implementation meets criteria from the description, like:
    val testInput0 = readInput("Day12_test0")
    val part2Result0 = part2(testInput0)
    println(part2Result0)
    check(part2Result0 == 36) { "Part2 failed0: $part2Result0" }

    val testInput = readInput("Day12_test")
    val part2Result = part2(testInput)
    println(part2Result)
    check(part2Result == 103) { "Part2 failed: $part2Result" }

    val testInput2 = readInput("Day12_test2")
    val part1Result2 = part2(testInput2)
    println(part1Result2)
    check(part1Result2 == 3509) { "Part2 failed 2: $part1Result2" }

    val input = readInput("Day12")
    val part2FinalResult = part2(input)
    println("Part2: $part2FinalResult")
    check(part2FinalResult == 152837) { "Part2 Final failed: $part2Result" }
}


private fun findPath2(currentCave: String, actualPath: List<String>, connectionMap: Map<String, List<String>>) {

//    val smallCaveVisitingCounters = actualPath.groupingBy { it }
//        .eachCount()
//        .filterNot { it.key == "start" || it.key.isLargeCave() }

    val smallCaveWithCurrentCaveVisitingCounters = actualPath.plus(currentCave).groupingBy { it }
        .eachCount()
        .filterNot { it.key == "start" || it.key.isLargeCave() }

    val smallCavesWithCurrentVisitedThreeTimesCounter = smallCaveWithCurrentCaveVisitingCounters.filter { it.value == 3 }.size

    val smallCavesWithCurrentVisitedTwoTimesCounter = smallCaveWithCurrentCaveVisitingCounters.filter { it.value == 2 }.size


//    val smallCaveThatVisitedTwiceCounter = smallCaveVisitingCounters
//        .filterNot { it.key == "start" || it.key.isLargeCave() || it.value < 2  }
//        .size
//
//    val currentCaveVisitCounter = actualPath.count { it == currentCave }

    if (
        (actualPath.isNotEmpty() && currentCave == "start")
        ||
        (
                currentCave.isSmallCave()
                        && (smallCavesWithCurrentVisitedThreeTimesCounter > 0 || smallCavesWithCurrentVisitedTwoTimesCounter > 1)
        )
    ) {


    } else {
        val newActualPath = actualPath.plus(currentCave)
        if (currentCave == "end") {
            allPaths.add(newActualPath)
        } else {
            val nextCaves = connectionMap[currentCave]!!
            for (cave in nextCaves) {
                findPath2(cave, newActualPath, connectionMap)
            }
        }
    }

}

private fun createConnectionMap2(input: List<String>): MutableMap<String, MutableList<String>> {
    val connectionMap = mutableMapOf<String, MutableList<String>>()
    input
        .forEach { line ->
            val connectionParts = line.split("-")
            if (connectionParts[0] == "start") {
                connectionMap.getOrPut(connectionParts[0]) { mutableListOf() }.add(connectionParts[1])
            } else if (connectionParts[1] == "start") {
                connectionMap.getOrPut(connectionParts[1]) { mutableListOf() }.add(connectionParts[0])
            } else if (connectionParts[0] == "end") {
                connectionMap.getOrPut(connectionParts[1]) { mutableListOf() }.add(connectionParts[0])
            } else if (connectionParts[1] == "end") {
                connectionMap.getOrPut(connectionParts[0]) { mutableListOf() }.add(connectionParts[1])
            } else {
                connectionMap.getOrPut(connectionParts[0]) { mutableListOf() }.add(connectionParts[1])
                connectionMap.getOrPut(connectionParts[1]) { mutableListOf() }.add(connectionParts[0])
            }
        }
    return connectionMap
}
