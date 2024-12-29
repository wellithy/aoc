package com.arxict.aoc.year2024

import com.arxict.aoc.common.*

class Day10(lines: List<String>) {
    val matrix = Matrix(lines.map { it.toList().map(Char::digitToInt) })

    val trailFinder = object : PathBacktrack<Point>() {
        override fun accept(candidate: List<Point>): Boolean =
            matrix[candidate.last()] == 9

        override fun neighbors(vertex: Point): Sequence<Point> =
            vertex.neighbors().filter { matrix.getOrNull(it) == matrix[vertex].inc() }
    }

    fun solve() =
        matrix.points().filter { matrix[it] == 0 }.map { start ->
            trailFinder.solve(start).toSet().map { it.last() }.toList().let { it.toSet().size to it.size }
        }.reduce { a, b -> a.first + b.first to a.second + b.second }
            .let { "${it.first} ${it.second}" }
}
