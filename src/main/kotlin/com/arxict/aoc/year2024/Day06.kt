package com.arxict.aoc.year2024
import com.arxict.aoc.common.*
class Day06(val lines: List<String>) {
    val start = '^'
    val block = '#'

    data class Location(val point: Point, val direction: Direction)

    fun Location.next(): Location = copy(point = point.move(direction))

    fun Location.turn() = copy(direction = direction.turn())

    fun MutableMatrix<*>.start() =
        rows.withIndex().firstNotNullOf { (row, line) ->
            line.withIndex().firstOrNull { it.value == start }?.let { Location(Point(row, it.index), Direction.Up) }
        }

    fun path(lists: MutableMatrix<Char>, start: Location): Set<Location>? = buildSet {
        var current = start
        while (true) {
            if (!add(current)) return null
            val next = current.next()
            current = if ((lists[next.point] ?: break) == block) current.turn()
            else next
        }
    }

    fun solve(): Pair<Int, Int> {
        val matrix = MutableMatrix<Char>(lines.map(String::toMutableList).toMutableList())
        val start = matrix.start()
        var part1 = 0
        var part2 = 0
        path(matrix, start)!!.distinctBy(Location::point).forEach { location ->
            part1++
            val orig = matrix[location.point]!!
            matrix[location.point] = block
            if (path(matrix, start) == null) part2++
            matrix[location.point] = orig
        }
        return part1 to part2
    }
}
