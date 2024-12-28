package com.arxict.aoc

import com.arxict.aoc.common.readResourceLines
import com.arxict.aoc.common.transpose
import kotlin.io.path.Path
import kotlin.io.path.forEachDirectoryEntry
import kotlin.io.path.isDirectory
import kotlin.io.path.name
import kotlin.test.Test
import kotlin.test.assertEquals

class All {
    @Test
    fun blah() {
        AocTest.check(AocYearDay(2015, 12))
    }

    @Test
    fun all() {
        AocTest.check()
    }

    @Test
    fun main() {
        AocTest.check(AocYearDay(2015, 6))
    }
}

object AocTest {
    private val root =
        Path("your_puzzle_input")

    private fun path(year: Int, name: String) =
        Path(year.toString(), name)

    fun example(year: Int, name: String): List<String> =
        readResourceLines(path(year, name))

    fun puzzle(year: Int, name: String): List<String> =
        readResourceLines(root.resolve(path(year, name)))

    private val readers = listOf(::example, ::puzzle)

    fun check(yearDay: AocYearDay, solutions: List<String>) =
        readers.forEachIndexed { index, reader ->
            solutions[index].takeIf(String::isNotBlank)?.let { expected ->
                assertEquals(expected, yearDay.actual(reader))
            }
        }

    private fun solutions(year: Int) = readers.map { it(year, "solutions.txt") }.transpose()

    fun check(yearDay: AocYearDay) =
        check(yearDay, solutions(yearDay.year)[yearDay.day.dec()])

    fun check(year: Int) =
        solutions(year).forEachIndexed { index, solution ->
            check(AocYearDay(year, index.inc()), solution)
        }

    fun check() =
        root.forEachDirectoryEntry {
            if (it.isDirectory())
                it.name.toIntOrNull()?.let(::check)
        }
}
