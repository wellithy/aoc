package com.arxict.aoc.common

class MutableMatrix<T>(val mutableRows: MutableList<MutableList<T>>) : Matrix<T>(mutableRows)

operator fun <T> MutableMatrix<T>.set(point: Point, value: T): T =
    with(point) { mutableRows[row].set(column, value) }

fun <T> MutableMatrix<T>.swap(a: Point, b: Point) {
    set(b, set(a, get(b)))
}
