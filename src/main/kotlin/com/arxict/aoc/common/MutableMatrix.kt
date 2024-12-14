package com.arxict.aoc.common

@JvmInline
value class MutableMatrix<T>(val rows: MutableList<MutableList<T>>)

operator fun <T> MutableMatrix<T>.set(point: Point, value: T): T =
    with(point) { rows[row].set(column, value) }

fun <T> MutableMatrix<T>.getOrNull(point: Point): T? =
    with(point) { rows.getOrNull(row)?.getOrNull(column) }
