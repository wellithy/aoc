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

fun <T> List<T>.remove(index: Int): List<T> =
    toMutableList().apply { removeAt(index) }

fun <T> List<T>.removeLastOrNull(): List<T>? =
    takeIf(List<T>::isNotEmpty)?.let { remove(lastIndex) }

fun <T> List<T>.removeLast(): List<T> =
    remove(lastIndex)

fun <T> List<T>.subList(from: Int): List<T> =
    subList(from, size)
