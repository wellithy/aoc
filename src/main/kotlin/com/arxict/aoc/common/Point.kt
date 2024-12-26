package com.arxict.aoc.common

import com.arxict.aoc.common.Direction.*
import com.arxict.aoc.year2024.Day14

data class Point(val row: Int, val column: Int) {
    companion object {
        val UP = Point(-1, 0)
        val RIGHT = Point(0, 1)
        val DOWN = -UP
        val LEFT = -RIGHT

        val NEIGHBORS = Direction.entries.map(Direction::toPoint)

        val UP_RIGHT = UP + RIGHT
        val UP_LEFT = UP + LEFT
        val DOWN_RIGHT = DOWN + RIGHT
        val DOWN_LEFT = DOWN + LEFT

        val EIGHT_NEIGHBORS = listOf(
            UP, UP_RIGHT, RIGHT, DOWN_RIGHT,
            DOWN, DOWN_LEFT, LEFT, UP_LEFT
        )
    }
}

operator fun Point.unaryMinus(): Point =
    Point(-row, -column)

operator fun Point.plus(other: Point): Point =
    Point(row + other.row, column + other.column)

operator fun Point.minus(other: Point): Point =
    Point(row - other.row, column - other.column)

operator fun Point.times(scale: Int): Point =
    Point(row * scale, column * scale)

operator fun Int.times(point: Point): Point =
    point * this

fun Point.ray(direction: Point): Sequence<Point> =
    generateSequence(this, direction::plus)

fun Point.segment(direction: Point, length: Int): Sequence<Point> =
    ray(direction).take(length)

fun Point.neighbors(): Sequence<Point> =
    Point.NEIGHBORS.asSequence().map(::plus)

fun Direction.toPoint(): Point = when (this) {
    Up -> Point.UP
    Right -> Point.RIGHT
    Down -> Point.DOWN
    Left -> Point.LEFT
}

fun Point.toDirection(): Direction? = when (this) {
    Point.UP -> Up
    Point.RIGHT -> Right
    Point.DOWN -> Down
    Point.LEFT -> Left
    else -> null
}

fun Point.neighbor(direction: Direction, count: Int = 1): Point =
    plus(direction.toPoint() * count)

fun Set<Point>.plot(rows: Int, columns: Int, space: Char = ' ', mark: Char = 'O'): List<String> =
    List<MutableList<Char>>(rows) { MutableList(columns) { space } }.let { canvas ->
        forEach { canvas[it.row][it.column] = mark }
        canvas.map { it.joinToString("") }
    }

fun List<String>.parse(rows: Int, columns: Int, space: Char = ' ', mark: Char = 'O'): Set<Point> = buildSet {
    require(this@parse.size == rows)
    this@parse.forEachIndexed { row, line ->
        require(line.length == columns)
        line.forEachIndexed { column, c ->
            if (c == mark) add(Point(row, column))
            else require(c == space)
        }
    }
}