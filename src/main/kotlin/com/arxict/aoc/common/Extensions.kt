package com.arxict.aoc.common

import com.arxict.aoc.common.Direction.*
import java.nio.file.Path

// Read file

fun Path.readLines(): List<String> =
    ClassLoader.getSystemResourceAsStream(toString())?.reader()?.readLines()
        ?: error("Can't read $this")

private val NON_DIGITS = Regex("""\D+""")
fun String.numbers() = trim().split(NON_DIGITS)
fun String.integers() = numbers().map(String::toInt)

// Backtrack

fun <T> Backtrack<T>.solve(root: T): Sequence<T> = sequence {
    val backtrack = this@solve
    val dq = ArrayDeque<T>().apply { add(root) }
    while (dq.isNotEmpty()) {
        val candidate = dq.removeFirst()
        if (backtrack.accept(candidate)) yield(candidate)
        backtrack.first(candidate)?.let {
            var test = it
            while (true) {
                dq.addLast(test)
                test = backtrack.next(test) ?: break
            }
        }
    }
}

// Point, Direction, Trail

fun Point.move(direction: Direction, count: Int = 1): Point =
    when (direction) {
        Up -> copy(row = row - count)
        Down -> copy(row = row + count)
        Right -> copy(column = column + count)
        Left -> copy(column = column - count)
    }

operator fun Point.plus(other: Point): Point =
    Point(row + other.row, column + other.column)

fun Trail.last(): Point =
    points.last()

val Trail.lastIndex: Int
    get() = points.lastIndex

operator fun Trail.plus(point: Point): Trail =
    Trail(points + point)

operator fun Trail.get(index: Int): Point =
    points[index]

fun Trail.replaceLast(last: Point): Trail =
    Trail(points.toMutableList().apply { set(lastIndex, last) })

fun Direction.turn(clockwise: Boolean = true): Direction =
    if (clockwise) when (this) {
        Up -> Right
        Right -> Down
        Down -> Left
        Left -> Up
    } else when (this) {
        Up -> Left
        Right -> Up
        Down -> Right
        Left -> Down
    }

// Matrix

fun Matrix(lines: List<String>): Matrix<Char> =
    Matrix(lines.map(String::toList))

private fun Matrix<*>.rowSize() =
    rows.first().size
        .also { first -> require(rows.map(List<*>::size).all(first::equals)) }

val Matrix<*>.rowRange: IntRange get() = 0..<rows.size
val Matrix<*>.columnRange: IntRange get() = 0..<rowSize()

operator fun Matrix<*>.contains(point: Point): Boolean =
    with(point) { row in rowRange && column in columnRange }

operator fun <T> Matrix<T>.get(point: Point): T? =
    with(point) { rows.getOrNull(row)?.getOrNull(column) }

fun <T> Matrix<T>.column(index: Int): List<T> =
    List(rows.size) { rows[it][index] }

fun <T> Matrix<T>.transpose(): Matrix<T> =
    Matrix(List(rowSize(), ::column))

fun Matrix<*>.points(): Sequence<Point> =
    rowRange.asSequence().flatMap { row ->
        columnRange.asSequence().map { column -> Point(row, column) }
    }

operator fun <T> MutableMatrix<T>.set(point: Point, value: T): T =
    with(point) { mutableRows[row].set(column, value) }

// misc
fun <T> Iterable<T>.frequency(): Map<T, Int> =
    groupingBy { it }.eachCount()
