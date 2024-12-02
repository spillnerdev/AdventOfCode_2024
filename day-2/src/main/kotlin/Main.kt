package dev.spillner

import dev.spillner.aoc2024.Day
import dev.spillner.aoc2024.interfaces.AoCTask
import kotlin.math.abs

class Day2 : AoCTask {
    override val day: Day = Day.TWO

    override fun partOne(input: String) {
        val data = prepData(input)
        val safeReports = data.count(::isSafe)
        println("Safe: $safeReports")
    }

    override fun partTwo(input: String) {
        val safeReports = prepData(input)
            .map(::alter)
            .count { pair -> pair.second.any { isSafe(it) } }
        println("Safe: $safeReports")
    }
}

fun prepData(input: String): List<List<Int>> {
    return input.trim()
        .lines()
        .map { it.split("\\s+".toRegex()) }
        .map { it.map { list -> list.toInt() } }
}

fun isSafe(report: List<Int>): Boolean {
    return (isAscending(report) || isDescending(report)) && isDifferenceWithinRange(report, 1..3)
}

fun isAscending(report: List<Int>): Boolean {
    return report.sorted() == report
}

fun isDescending(report: List<Int>): Boolean {
    return report.sortedDescending() == report
}

fun isDifferenceWithinRange(report: List<Int>, range: IntRange): Boolean {
    return report.zipWithNext()
        .map { (a, b) -> abs(a - b) }
        .all { it >= range.first && it <= range.last }
}

fun <T> alter(report: List<T>): Pair<List<T>, List<List<T>>> {
    return report to report.indices.map {
        val current = report.toMutableList()
        current.removeAt(it)
        current
    }
}

fun main() {
    Day2().run()
}
