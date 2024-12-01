package dev.spillner

import dev.spillner.aoc2024.Day
import dev.spillner.aoc2024.web.DataImporter
import kotlin.math.abs

/**
 *
 * @author Lukas Spillner
 */
fun main() {
    partOne()
    partTwo()
}

private fun partOne() {
    val (listA, listB) = getLists()
    listA.sort()
    listB.sort()

    listA.zip(listB).sumOf { p -> abs(p.first - p.second) }.let { println("Result part one: $it") }
}

private fun partTwo() {
    val (listA, listB) = getLists()

    val eachCount = listB.groupingBy { it }.eachCount()
    listA.sumOf {
        (it * eachCount.getOrDefault(it, 0))
    }.let { println("Result part two: $it") }

}


private fun getLists(): Pair<MutableList<Int>, MutableList<Int>> {
    val input = DataImporter().getInput(Day.ONE)
    val listA = mutableListOf<Int>()
    val listB = mutableListOf<Int>()
    input.trim().split("\n").forEach {
        val split = it.trim().split("\\s+".toRegex())
        if (split.size == 2) {
            listA.add(split[0].toInt())
            listB.add(split[1].toInt())
        }
    }
    return Pair(listA, listB)
}
