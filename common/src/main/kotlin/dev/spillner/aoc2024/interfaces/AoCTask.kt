package dev.spillner.aoc2024.interfaces

import dev.spillner.aoc2024.Day
import dev.spillner.aoc2024.web.DataImporter

/**
 *
 * @author Lukas Spillner
 */
interface AoCTask : Runnable {
    val day: Day

    fun partOne(input: String)

    fun partTwo(input: String)

    fun getInput() = DataImporter().getInput(day)

    override fun run() {
        partOne(getInput())
        partTwo(getInput())
    }
}
