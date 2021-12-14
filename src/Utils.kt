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

fun String.isLargeCave() = this.all { it.isUpperCase() }

fun String.isSmallCave() = this.all { it.isLowerCase() }

fun findRating(input: List<String>, conditionToRetrieveZeros: (groupedByBit: Map<Char, List<String>>) -> Boolean): String {
    val binaryLength = input.first().length
    val fold = (0 until binaryLength)
        .fold(input) { acc, indexOfBit ->
            if (acc.size > 1) {
                val grouped = acc.groupBy { it[indexOfBit] }
                if (conditionToRetrieveZeros(grouped)) {
                    grouped['0']!!
                } else {
                    grouped['1']!!
                }
            } else {
                acc
            }
        }
        .first()

    return fold
}