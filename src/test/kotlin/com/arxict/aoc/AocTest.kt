package com.arxict.aoc

import com.arxict.aoc.common.resourceAsPath
import com.arxict.aoc.common.transpose
import kotlin.io.path.*
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.primaryConstructor
import kotlin.test.Test
import kotlin.test.assertEquals

class All {
    @Test
    fun blah() {
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

data class AocYearDay(val year: Int, val day: Int)

private val AocYearDay.dayStr get() = "Day${day.toString().padStart(2, '0')}"
private val AocYearDay.clazz get() = Class.forName("com.arxict.aoc.year$year.$dayStr").kotlin

private fun AocYearDay.func(name: String, vararg params: Any?): Any? =
    clazz.declaredFunctions.filter { it.name == name }.takeIf { it.isNotEmpty() }?.single()?.call(*params)

fun AocYearDay.actual(lines: (Int, String) -> List<String>): String {
    val instance = clazz.primaryConstructor!!.call(lines(year, "$dayStr.txt"))
    return func("solve", instance)?.toString() ?: "${func("part1", instance)} ${func("part2", instance)}"
}


object AocTest {
    private val root = Path(".aoc")
    private fun path(year: Int, name: String) = Path(year.toString(), name)

    fun example(year: Int, name: String): List<String> = resourceAsPath(path(year, name).toString()).readLines()
    fun puzzle(year: Int, name: String): List<String> = root.resolve(path(year, name)).readLines()

    private val readers = listOf(::example, ::puzzle)

    fun check(yearDay: AocYearDay, solutions: List<String>) {
        if (solutions.all(String::isBlank)) return
        readers.forEachIndexed { index, reader ->
            solutions[index].takeIf(String::isNotBlank)?.let { expected ->
                assertEquals(expected, yearDay.actual(reader))
            }
        }
    }

    private fun solutions(year: Int) = readers.map { it(year, "solutions.txt")}.transpose()

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
