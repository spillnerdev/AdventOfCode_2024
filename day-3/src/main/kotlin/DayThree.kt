package dev.spillner

import dev.spillner.aoc2024.Day
import dev.spillner.aoc2024.interfaces.AoCTask

class Day3 : AoCTask {
    override val day: Day = Day.THREE

    private val regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()

    private val regexTwo = "(mul\\((\\d{1,3}),(\\d{1,3})\\))|(do\\(\\))|(don't\\(\\))".toRegex()

    override fun partOne(input: String) {
        regex.findAll(input)
            .map { match ->
                match.groupValues.subList(1, match.groupValues.size).map { it.toInt() }.reduce(Int::times)
            }.sum().let { println("Result: $it") }
    }

    override fun partTwo(input: String) {
        var enabled = true
        regexTwo.findAll(input).fold(0) { value, next ->
            if (next.groups[4] != null || next.groups[4]?.value?.isBlank() == false) {
                enabled = true
                return@fold value
            }
            if (next.groups[5] != null || next.groups[5]?.value?.isBlank() == false) {
                enabled = false
                return@fold value
            }

            if (enabled) {
                return@fold value + next.groupValues.subList(2, 4).map { it.toInt() }.reduce(Int::times)
            }
            value
        }.let { println("Result: $it") }
    }
}

fun main() {
    Day3().run()
}
