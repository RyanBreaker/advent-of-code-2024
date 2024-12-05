package day_02

import java.io.File

fun main() {
    val input = File("src/day_02/input.txt").readLines()
        .map { it.split(" ").map(String::toInt) }

    val outputPartOne = input.map(::isReportSafePartOne).filter { it }
    val outputPartTwo = input.map(::isReportSafePartTwo).filter { it }

    println("Part One: ${outputPartOne.size}")
    println("Part Two: ${outputPartTwo.size}")
}

enum class Direction {
    Increasing, Decreasing, Unknown
}

fun isReportSafePartOne(report: List<Int>): Boolean {
    var direction = Direction.Unknown

    for (i in 0 until report.size - 1) {
        val a = report[i]
        val b = report[i + 1]

        if (a == b) {
            return false
        }

        if (a > b) {
            if (direction == Direction.Unknown) direction = Direction.Decreasing
            else if (direction == Direction.Increasing) return false

            if (a - b > 3) {
                return false
            }
        } else {
            if (direction == Direction.Unknown) direction = Direction.Increasing
            else if (direction == Direction.Decreasing) return false

            if (b - a > 3) {
                return false
            }
        }
    }

    return true
}

// WIP
fun isReportSafePartTwo(report: List<Int>): Boolean {
    var experiencedUnsafe = false
    var direction = Direction.Unknown

    fun experiencedUnsafe(): Boolean {
        if (experiencedUnsafe) return true
        experiencedUnsafe = true
        return false
    }

    for (i in 0 until report.size - 1) {
        val a = report[i]
        val b = report[i + 1]

        if (a == b) {
            if (experiencedUnsafe()) return false
        }

        if (a > b) {
            if (direction == Direction.Unknown) direction = Direction.Decreasing
            else if (direction == Direction.Increasing) {
                if (experiencedUnsafe()) return false
            }

            if (a - b > 3) {
                if (experiencedUnsafe()) return false
            }
        } else {
            if (direction == Direction.Unknown) direction = Direction.Increasing
            else if (direction == Direction.Decreasing) {
                if (experiencedUnsafe()) return false
            }

            if (b - a > 3) {
                if (experiencedUnsafe()) return false
            }
        }
    }

    return true
}
