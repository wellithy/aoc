package com.arxict.aoc.year2024

import com.arxict.aoc.common.*
import com.arxict.aoc.common.Direction.*

class Day10(val lines: List<String>) {
    fun solve(matrix: Matrix<Int>, start: Point): Pair<Int, Int> {
        fun Point.direction(after: Point): Direction = when {
            after.column == column.inc() -> Right
            after.column == column.dec() -> Left
            after.row == row.inc() -> Down
            after.row == row.dec() -> Up
            else -> error("bug")
        }

        val directions = Direction.entries

        class TrailFinder() : Backtrack<Trail> {
            fun solve() = if (matrix[start] != 0) emptySequence() else solve(Trail(listOf(start)))
            override fun accept(candidate: Trail): Boolean =
                matrix[candidate.last()] == 9

            fun nextTail(current: Point, start: Int = 0): Point? =
                (start..directions.lastIndex)
                    .map { current.neighbor(directions[it]) }
                    .firstOrNull { matrix.getOrNull(it) == matrix[current].inc() }

            override fun first(candidate: Trail): Trail? =
                nextTail(candidate.last())?.let { candidate + it }

            override fun next(candidate: Trail): Trail? {
                val prev = candidate[candidate.lastIndex.dec()]
                val dir = directions.indexOf(prev.direction(candidate.last()))
                return nextTail(prev, dir.inc())?.let(candidate::replaceLast)
            }
        }

        val paths = TrailFinder().solve().toSet()
        return paths.map(Trail::last).toSet().size to paths.size
    }

    fun solve(): Pair<Int, Int> {
        val map = Matrix(lines.map { it.toList().map(Char::digitToInt) })
        return map.points().map { solve(map, it) }
            .reduce { a, b -> a.first + b.first to a.second + b.second }

    }
}
