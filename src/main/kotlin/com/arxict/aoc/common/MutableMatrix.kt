package com.arxict.aoc.common

class MutableMatrix<T>(val mutableRows: MutableList<MutableList<T>>) : Matrix<T>(mutableRows)

operator fun <T> MutableMatrix<T>.set(point: Point, value: T): T =
    with(point) { mutableRows[row].set(column, value) }

fun <T> MutableMatrix<T>.swap(a: Point, b: Point) {
    set(b, set(a, get(b)))
}

fun <T> MutableMatrix(rows: Int, columns: Int, element: T): MutableMatrix<T> =
    MutableMatrix(MutableList<MutableList<T>>(rows) { MutableList(columns) { element } })

fun <T> MutableMatrix<T>.setAll(points: Sequence<Point>, value: (Point) -> T) =
    points.forEach { this[it] = value(it) }

fun <T> MutableMatrix(points: Sequence<Point>, rows: Int, columns: Int, space: T, mark: T): MutableMatrix<T> =
    MutableMatrix(rows, columns, space).apply { setAll(points) { mark } }

fun MutableMatrix(points: Sequence<Point>, rows: Int, columns: Int): MutableMatrix<Char> =
    MutableMatrix(points, rows, columns, ' ', '0')
