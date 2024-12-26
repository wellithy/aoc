package com.arxict.aoc

import com.arxict.aoc.common.transpose
import kotlin.io.path.*
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.primaryConstructor
import kotlin.test.assertEquals

fun main() {
    AocTest.check()
}

object AocTest {
    private val thePackage = javaClass.name.substringBeforeLast('.')
    private val root = Path("../KEEP/aoc")
    private fun path(year: Int, name: String) = Path(year.toString(), name).toString()
    fun example(year: Int, name: String) =
        ClassLoader.getSystemResourceAsStream(path(year, name))!!.reader().readLines()

    fun puzzle(year: Int, name: String) = root.resolve(path(year, name)).readLines()
    private val readers = listOf(::example, ::puzzle)

    fun check(year: Int, day: Int, solutions: List<String>) {
        if (solutions.all(String::isBlank)) return
        val dayStr = "Day${day.toString().padStart(2, '0')}"
        val clazz = Class.forName("$thePackage.year$year.$dayStr").kotlin
        val functions = clazz.declaredFunctions
        val solve = functions.filter { it.name == "solve" }.takeIf { it.isNotEmpty() }?.single()
        fun f(i: Int, x: Any) = functions.single { it.name == "part$i" }.call(x)
        readers.forEachIndexed { index, reader ->
            solutions[index].takeIf(String::isNotBlank)?.let { expected ->
                val instance = clazz.primaryConstructor!!.call(reader(year, "$dayStr.txt"))
                val actual = solve?.call(instance) ?: "${f(1, instance)} ${f(2, instance)}"
                assertEquals(expected, actual)
            }
        }
    }

    private fun solutions(year: Int) = readers.map { it(year, "solutions.txt") }.transpose()

    fun check(year: Int, day: Int) =
        check(year, day, solutions(year)[day.dec()])

    fun check(year: Int) =
        solutions(year).forEachIndexed { index, solution ->
            check(year, index.inc(), solution)
        }

    fun check() =
        root.forEachDirectoryEntry {
            if (it.isDirectory())
                it.name.toIntOrNull()?.let(::check)
        }
}
