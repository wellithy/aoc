package com.arxict.aoc.year2024

import com.arxict.aoc.common.*

class Day16(lines: List<String>) {
    val maze = Matrix(lines)
    fun Point.valid() = maze.getOrNull(this)?.equals(WALL) == false
    val start = Point(maze.columnSize - 2, 1).also { require(maze[it] == 'S') }
    val end = Point(1, maze.rowSize - 2).also { require(maze[it] == 'E') }

    fun neighbors(point: Point) = point.neighbors().filter { it.valid() }

    fun List<Point>.directions() =
        sequenceOf(Direction.Right) + asSequence().windowed(2).map { (a, b) -> (b - a).toDirection() }

    fun List<Point>.cost(): Int =
        lastIndex + directions().windowed(2).sumOf { (x, y) -> if (x == y) 0 else 1000.toInt() }

    fun solve(): Pair<Int, Int> =
        buildMap {
            dijkstra(::neighbors, start, end, compareBy { it.cost() }).forEach { path ->
                getOrPut(path.cost(), ::mutableSetOf).addAll(path)
            }
        }.minBy { it.key }.let { it.key to it.value.size }

    companion object {
        const val WALL = '#'
    }
}
