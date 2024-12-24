package com.arxict.aoc

import java.nio.file.Path
import kotlin.io.path.Path
import kotlin.io.readLines

open class BaseYearTest(year: String) {
    fun puzzle(name: String) = puzzle.resolve(name).readLines()
    fun example(name: String) = example.resolve(name).readLines()

    private val example = Path(year)
    private val puzzle = example.resolve("__aoc_my_puzzle_input__")
    private fun Path.readLines(): List<String> =
        ClassLoader.getSystemResourceAsStream(toString())?.reader()?.readLines()
            ?: error("Can't read $this")
}