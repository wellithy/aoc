package com.arxict.aoc.test

import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.streams.asSequence

fun input(): List<String> =
    readFileLines(inputDir)

private fun readFileLines(dir: Path, file: String = callerDay(), extension: String = ".txt"): List<String> =
    readResourceLines(dir.resolve("$file$extension").toString())

private fun readResourceLines(resourceName: String): List<String> =
    ClassLoader.getSystemResourceAsStream(resourceName)!!.bufferedReader().readLines()

private val DAY = Regex("""(Day\d\d)""")
private fun callerDay(): String =
    StackWalker.getInstance().walk { s ->
        s.asSequence().firstNotNullOf { DAY.find(it.className) }.groupValues[1]
    }

private val inputDir: Path =
    Path("your_puzzle_input")
