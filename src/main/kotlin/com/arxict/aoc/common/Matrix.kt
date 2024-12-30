package com.arxict.aoc.common

open class Matrix<T>(val matrix: List<List<T>>)

fun <T> Matrix<T>.lines(itemSeparator: String = "", asString: T.() -> String = { toString() }): List<String> =
    matrix.map { it.joinToString(itemSeparator) { it.asString() } }

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

val Matrix<*>.columns: Int
    get() = matrix.first().size
        .also { first -> require(matrix.map(List<*>::size).all(first::equals)) }

val Matrix<*>.rows: Int
    get() = matrix.size

fun <T> Matrix<T>.getOrNull(point: Point): T? =
    matrix.getOrNull(point.row)?.getOrNull(point.column)

operator fun Matrix<*>.contains(point: Point): Boolean =
    getOrNull(point) != null

operator fun <T> Matrix<T>.get(point: Point): T =
    matrix[point.row][point.column]

operator fun <T> Matrix<T>.get(row: Int): List<T> =
    matrix[row]

fun <T> Matrix<T>.getNotNull(points: Sequence<Point>): Sequence<T> =
    points.mapNotNull(::getOrNull)

fun <T> Matrix<T>.column(index: Int): List<T> =
    List(matrix.size) { matrix[it][index] }

fun <T> Matrix<T>.transpose(): Matrix<T> =
    Matrix(List(columns, ::column))

fun <T> List<List<T>>.transpose(): List<List<T>> =
    Matrix(this).transpose().matrix

fun Matrix<*>.points(): Sequence<Point> =
    (0..<rows).asSequence().flatMap { row ->
        (0..<columns).asSequence().map { column -> Point(row, column) }
    }

fun <T> Matrix<T>.toMutableMatrix(): MutableMatrix<T> =
    matrix.asSequence().map(List<T>::toMutableList).toMutableList().let(::MutableMatrix)

fun <T> Matrix<T>.asPoints(space: T, mark: T): Sequence<Point> = sequence {
    matrix.forEachIndexed { row, line ->
        line.forEachIndexed { column, c ->
            if (c == mark) yield(Point(row, column))
            else require(c == space)
        }
    }
}

fun Matrix<Char>.asPoints(): Sequence<Point> =
    asPoints(' ', 'O')

// 1xM * Mx1 = 1x1
operator fun List<Int>.times(right: List<Int>): Int =
    indices.sumOf { this[it] * right[it] }

// NxM * Mx1 = Nx1
operator fun Matrix<Int>.times(right: List<Int>): List<Int> =
    matrix.map { it * right }

// 1xM * MxK = 1xK
operator fun List<Int>.times(right: Matrix<Int>): List<Int> =
    (0..<right.columns).map { column ->
        indices.sumOf { this[it] * right[it][column] }
    }

// NxM * MxK = NxK
operator fun Matrix<Int>.times(right: Matrix<Int>): Matrix<Int> =
    Matrix(matrix.map { it * right })
