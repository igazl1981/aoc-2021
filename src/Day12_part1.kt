private const val width = 10
private const val height = 10

private val allPaths = mutableListOf<List<String>>()

fun main() {

    fun part1(input: List<String>): Int {
        allPaths.removeAll { true }
        val connectionMap = createConnectionMap(input)
        findPath("start", mutableListOf(), connectionMap)

        return allPaths.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day12_test")
    val part1Result = part1(testInput)
    println(part1Result)
    check(part1Result == 19) { "Part1 failed: $part1Result" }

    val testInput2 = readInput("Day12_test2")
    val part1Result2 = part1(testInput2)
    println(part1Result2)
    check(part1Result2 == 226) { "Part1 failed 2: $part1Result2" }


    val input = readInput("Day12")
    val part1FinalResult = part1(input)
    println("Part1: $part1FinalResult")
    check(part1FinalResult == 1691) { "Part1  Final failed: $part1FinalResult" }
}

private fun findPath(currentCave: String, actualPath: List<String>, connectionMap: Map<String, List<String>>) {

        if (currentCave.isSmallCave() && actualPath.contains(currentCave)) {

        } else {
            val newActualPath = actualPath.plus(currentCave)
            if (currentCave == "end") {
                allPaths.add(newActualPath)
            } else {
                val nextCaves = connectionMap[currentCave]!!
                for (cave in nextCaves) {
                    findPath(cave, newActualPath, connectionMap)
                }
            }
        }

}

fun createConnectionMap(input: List<String>): MutableMap<String, MutableList<String>> {
    val connectionMap = mutableMapOf<String, MutableList<String>>()
    input
        .forEach { line ->
            val connectionParts = line.split("-")
            connectionMap.getOrPut(connectionParts[0]) { mutableListOf() }.add(connectionParts[1])
            connectionMap.getOrPut(connectionParts[1]) { mutableListOf() }.add(connectionParts[0])
        }
    return connectionMap
}