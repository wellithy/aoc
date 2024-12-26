package com.arxict.aoc.common

open class Matrix<T>(val rows: List<List<T>>)

fun <T> Matrix<T>.lines(itemSeparator: String = "", asString: T.() -> String = { toString() }): List<String> =
    rows.map { it.joinToString(itemSeparator) { it.asString() } }

fun <T> Matrix<T>.asString(
    lineSeparator: String = System.lineSeparator(),
    itemSeparator: String = "",
    asString: T.() -> String = { toString() }
): String =
    lines(itemSeparator, asString).joinToString(lineSeparator)

fun <T> Matrix(vararg lists: List<T>): Matrix<T> =
    Matrix(lists.toList())

fun <T, U> Matrix(list: List<U>, transformer: (U) -> List<T>): Matrix<T> =
    Matrix(list.map(transformer))

fun Matrix(lines: List<String>): Matrix<Char> =
    Matrix(lines, String::toList)

val Matrix<*>.rowSize: Int
    get() = rows.first().size
        .also { first -> require(rows.map(List<*>::size).all(first::equals)) }

val Matrix<*>.columnSize: Int
    get() = rows.size

val Matrix<*>.rowRange: IntRange
    get() = 0..<columnSize
val Matrix<*>.columnRange: IntRange
    get() = 0..<rowSize

operator fun Matrix<*>.contains(point: Point): Boolean =
    point.row in rowRange && point.column in columnRange

fun <T> Matrix<T>.getOrNull(point: Point): T? =
    rows.getOrNull(point.row)?.getOrNull(point.column)

operator fun <T> Matrix<T>.get(point: Point): T =
    rows[point.row][point.column]

operator fun <T> Matrix<T>.get(row: Int): List<T> =
    rows[row]

fun <T> Matrix<T>.getNotNull(points: Sequence<Point>): Sequence<T> =
    points.mapNotNull(::getOrNull)

fun <T> Matrix<T>.column(index: Int): List<T> =
    List(rows.size) { rows[it][index] }

fun <T> Matrix<T>.transpose(): Matrix<T> =
    Matrix(List(rowSize, ::column))

fun <T> List<List<T>>.transpose(): List<List<T>> =
    Matrix(this).transpose().rows

fun Matrix<*>.points(): Sequence<Point> =
    rowRange.asSequence().flatMap { row ->
        columnRange.asSequence().map { column -> Point(row, column) }
    }

fun <T> Matrix<T>.toMutableMatrix(): MutableMatrix<T> =
    rows.asSequence().map(List<T>::toMutableList).toMutableList().let(::MutableMatrix)
