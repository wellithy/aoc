package com.arxict.aoc.common

import com.arxict.aoc.common.Direction.*
import kotlin.ranges.contains

data class Point(val row: Int, val column: Int) {
    companion object {
        val ORIGIN = Point(0, 0)
    }
}

fun Point.neighbor(direction: Direction, count: Int = 1): Point =
    when (direction) {
        Up -> Point(row - count, column)
        Down -> Point(row + count, column)
        Right -> Point(row, column + count)
        Left -> Point(row, column - count)
    }

fun Point.neighbors(): Sequence<Point> =
    Direction.entries.asSequence().map(::neighbor)

fun Point.ray(direction: Direction): Sequence<Point> =
    generateSequence(this) { it.neighbor(direction) }

fun Point.rays(): List<Sequence<Point>> =
    Direction.entries.map(::ray)

operator fun Point.plus(other: Point): Point =
    Point(row + other.row, column + other.column)

operator fun Point.minus(other: Point): Point =
    Point(row - other.row, column - other.column)

operator fun Point.times(scale: Int): Point =
    Point(row * scale, column * scale)

fun Point.segment(direction: Direction, length: Int): Sequence<Point> =
    generateSequence(this) { it.neighbor(direction) }.take(length)


