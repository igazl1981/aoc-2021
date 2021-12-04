import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

fun readInputForDay2(name: String) = readInput(name)
    .map { line ->
        line.split(" ")
            .let { Pair(Direction.fromString(it[0]), it[1].toInt()) }
    }

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Returns the size of the list and 0 if the list is null
 */
fun getSizeOfItems(items: List<String>?) = items?.size ?: 0


fun findRating(input: List<String>, conditionToRetriveZeros: (groupedByBit: Map<Char, List<String>>) -> Boolean): String {
    val binaryLength = input.first().length
    var filteredInput = input
    (0 until binaryLength).asSequence().takeWhile { filteredInput.size > 1 }
        .forEach { i ->
            val grouped = filteredInput.groupBy { it[i] }
            if (conditionToRetriveZeros(grouped)) {
                filteredInput = grouped['0']!!
            } else {
                filteredInput = grouped['1']!!
            }
        }

    return filteredInput.first()
}