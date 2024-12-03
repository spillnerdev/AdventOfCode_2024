package dev.spillner.aoc2024.web

import dev.spillner.aoc2024.Day
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import java.io.File
import java.nio.charset.StandardCharsets

/**
 *
 * @author Lukas Spillner
 */
object DataImporter {

    private const val FILE_TEMPLATE = "input-day-%d.txt"
    private const val INPUT_CACHE = ".\\input"
    private const val SESSION_TOKEN = "<YourSessionTokenHere>"
    private const val USER_AGENT = "github.com/spillnerdev/AdventOfCode_2024"

    fun getInput(day: Day): String {
        val fileName = FILE_TEMPLATE.format(day.value)
        return with(lookUpCache(fileName) ?: createFile(day, fileName)) {
            readText(StandardCharsets.UTF_8)
        }
    }

    private fun lookUpCache(fileName: String): File? {
        val cache = File(INPUT_CACHE)
        if (!cache.isDirectory) throw IllegalArgumentException("Cache directory is not a directory")
        val targets = cache.listFiles { file -> file.name.equals(fileName, true) }

        if (targets?.size!! >= 2) throw IllegalStateException("Invalid amount of input files: ${targets.size}")
        return targets.firstOrNull()

    }

    private fun createFile(day: Day, fileName: String): File {
        val file = File(INPUT_CACHE, fileName)
        file.createNewFile().let { if (!it) throw IllegalStateException("Cannot create file: $file") }

        val input = requestInput(day)

        file.writeText(input, StandardCharsets.UTF_8)
        return file
    }

    private fun requestInput(day: Day): String {
        val httpClient = HttpClient(CIO)
        val input = runBlocking {
            val url = "https://adventofcode.com/2024/day/${day.value}/input"

            val resp = httpClient.get(url) {
                headers {
                    append(HttpHeaders.UserAgent, USER_AGENT)
                    append(HttpHeaders.Cookie, SESSION_TOKEN)
                }
            }
            resp.bodyAsText()
        }
        httpClient.close()
        return input
    }
}
