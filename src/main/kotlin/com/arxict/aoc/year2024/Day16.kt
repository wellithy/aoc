package com.arxict.aoc.year2024

import com.arxict.aoc.common.*

class Day16(lines: List<String>) {
    val maze = Matrix(lines, String::toList)
    fun Point.valid() = maze.getOrNull(this)?.equals(WALL) == false
    val start = Point(maze.rows - 2, 1).also { require(maze[it] == 'S') }
    val end = Point(1, maze.columns - 2).also { require(maze[it] == 'E') }

    fun neighbors(point: Point) = point.neighbors().filter { it.valid() }

    fun List<Point>.directions() =
        sequenceOf(Direction.Right) + asSequence().windowed(2).map { (a, b) -> (b - a).toDirection() }

    fun List<Point>.cost(): Int =
        lastIndex + directions().windowed(2).sumOf { (x, y) -> if (x == y) 0 else 1000.toInt() }

    fun part1(): Int? =
        dijkstra(::neighbors, start, end, compareBy { it.cost() })?.cost()

    fun part2() = "?"

    companion object {
        const val WALL = '#'
    }
}
