package com.arxict.aoc

import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.primaryConstructor

data class AocYearDay(val year: Int, val day: Int)

private val AocYearDay.dayStr get() = "Day${day.toString().padStart(2, '0')}"
private val packageName = AocYearDay::class.qualifiedName!!.substringBeforeLast(".")
private val AocYearDay.clazz get() = Class.forName("$packageName.year$year.$dayStr").kotlin

private fun AocYearDay.func(name: String, vararg params: Any?): Any? =
    clazz.declaredFunctions.filter { it.name == name }.takeIf { it.isNotEmpty() }?.single()?.call(*params)

fun AocYearDay.actual(lines: (Int, String) -> List<String>): String {
    val instance = clazz.primaryConstructor!!.call(lines(year, "$dayStr.txt"))
    return func("solve", instance)?.toString() ?: "${func("part1", instance)} ${func("part2", instance)}"
}
