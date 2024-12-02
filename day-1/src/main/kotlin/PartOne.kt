package dev.spillner

import dev.spillner.aoc2024.Day
import dev.spillner.aoc2024.interfaces.AoCTask
import kotlin.math.abs


class Day1 : AoCTask {
    override val day: Day = Day.ONE

    override fun partOne(data: String) {
        val (listA, listB) = getLists(data)
        listA.sort()
        listB.sort()
        listA.zip(listB).sumOf { p -> abs(p.first - p.second) }.let { println("Result part one: $it") }
    }

    override fun partTwo(data: String) {
        val (listA, listB) = getLists(data)
        val eachCount = listB.groupingBy { it }.eachCount()
        listA.sumOf { it * eachCount.getOrDefault(it, 0) }.let { println("Result part two: $it") }
    }

    private fun getLists(input: String): Pair<MutableList<Int>, MutableList<Int>> {
        val listA = mutableListOf<Int>()
        val listB = mutableListOf<Int>()
        input.trim().lines().forEach {
            val split = it.trim().split("\\s+".toRegex())
            listA.add(split[0].toInt())
            listB.add(split[1].toInt())
        }
        return Pair(listA, listB)
    }
}


fun main() {
    Day1().run()
}
