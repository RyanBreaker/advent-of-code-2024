package day_01

import java.io.File

private var cachedInputs: Pair<List<Int>, List<Int>>? = null

fun getInputs(): Pair<List<Int>, List<Int>> {
    cachedInputs?.let { return it }

    val input = File("src/day_01/input.txt").readText()
        .split("   ", "\r\n")
        .map { it.toInt() }

    var isEven = false
    val inputs = input.partition {
        isEven = !isEven
        isEven
    }.let { (a, b) -> a.sorted() to b.sorted() }

    cachedInputs = inputs
    return inputs
}

fun partOne(): Int {
    val (first, second) = getInputs()

    var sumOfDiffs = 0
    for (i in 0 until first.size) {
        val min = minOf(first[i], second[i])
        val max = maxOf(first[i], second[i])
        sumOfDiffs += max - min
    }

    return sumOfDiffs
}

fun partTwo(): Int {
    val (first, second) = getInputs()

    var sumOfSimilarities = 0
    for (n in first) {
        sumOfSimilarities += n * second.count { it == n }
    }

    return sumOfSimilarities
}

fun main() {
    println("Part One: ${partOne()}")
    println("Part Two: ${partTwo()}")
}
