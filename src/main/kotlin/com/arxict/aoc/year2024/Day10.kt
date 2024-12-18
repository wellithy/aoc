package com.arxict.aoc.year2024

import com.arxict.aoc.common.*

class Day10(val lines: List<String>) {
    fun solve(matrix: Matrix<Int>, start: Point): Pair<Int, Int> {

        class TrailFinder() : Backtrack<Trail> {
            fun solve() = if (matrix[start] != 0) emptySequence() else solve(Trail(listOf(start)))
            override fun accept(candidate: Trail): Boolean =
                matrix[candidate.last()] == 9

            fun nextTail(current: Point, directions: List<Direction>): Point? =
                directions.map(current::neighbor)
                    .firstOrNull { matrix.getOrNull(it) == matrix[current].inc() }

            override fun first(candidate: Trail): Trail? =
                nextTail(candidate.last(), Direction.entries)?.let { candidate + it }

            override fun next(candidate: Trail): Trail? {
                val prev = candidate[candidate.lastIndex.dec()]
                val dir = (candidate.last() - prev).toDirection()!!.after()
                return nextTail(prev, dir)?.let(candidate::replaceLast)
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
