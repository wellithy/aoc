package com.arxict.aoc.common

import java.nio.file.Path
import kotlin.io.path.Path


fun resourceAsPath(resource: String): Path = ClassLoader.getSystemResource(resource).path.let(::Path)

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

fun <T> List<T>.replaceAt(end: T, index: Int): List<T> =
    toMutableList().apply { set(index, end) }

fun <T> List<T>.subList(from: Int): List<T> =
    subList(from, size)

fun <T : Comparable<T>> Sequence<T>.minMax(): Pair<T, T> {
    val iterator = iterator()
    var min = iterator.next()
    var max = min
    iterator.forEachRemaining {
        min = min.coerceAtMost(it)
        max = max.coerceAtLeast(it)
    }
    return min to max
}