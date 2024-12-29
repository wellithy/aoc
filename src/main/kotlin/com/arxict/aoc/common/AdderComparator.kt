package com.arxict.aoc.common

interface AdderComparator<T> : Comparator<T> {
    fun add(a: T, b: T): T
}

fun <T : Comparable<T>> AdderComparator(plus: T.(T) -> T) = object : AdderComparator<T> {
    override fun add(a: T, b: T): T = a.plus(b)

    override fun compare(o1: T?, o2: T?): Int = compareValues(o1, o2)
}

val intAdderComparator = AdderComparator<Int>(Int::plus)
