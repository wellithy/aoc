package com.arxict.aoc.common

import java.nio.file.Path

// Read file

fun Path.readLines(): List<String> =
    ClassLoader.getSystemResourceAsStream(toString())?.reader()?.readLines()
        ?: error("Can't read $this")

private val NON_DIGITS = Regex("""\D+""")

fun <T> CharSequence.numbers(toNum: String.() -> T): List<T> =
    NON_DIGITS.splitToSequence(trim()).map(toNum).toList()

fun CharSequence.integers(): List<Int> =
    numbers(String::toInt)

fun CharSequence.longs(): List<Long> =
    numbers(String::toLong)

// misc
fun <T> Iterable<T>.frequency(): Map<T, Int> =
    groupingBy { it }.eachCount()
