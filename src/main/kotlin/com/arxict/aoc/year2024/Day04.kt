package com.arxict.aoc.year2024
import com.arxict.aoc.common.*

class Day04(lines: List<String>) {
    val input = Matrix(lines)

    fun Trail(direction: Point, length: Int): Trail =
        Trail((0..<length).map { Point(it * direction.row, it * direction.column) })

    fun Matrix<Char>.get(point: Point, trail: Trail): String? =
        trail.points.map { getOrNull(point + it) ?: return@get null }.joinToString("")

    fun Matrix<Char>.search(words: Set<String>, possible: List<Trail>): Int {
        val firsts = words.map(String::first).toSet()
        return points().sumOf { point ->
            val c = getOrNull(point)
            if (c !in firsts) 0
            else possible
                .mapNotNull { get(point, it) }
                .count(words::contains)
        }
    }

    fun part1(): Int {
        val word = "XMAS"
        val directions = listOf(0 to 1, 1 to 1, 1 to 0, 1 to -1).map { Point(it.first, it.second) }
        val trails = directions.map { Trail(it, word.length) }
        return input.search(setOf(word, word.reversed()), trails)
    }

    fun part2(): Int {
        val word = "MAS"
        val rev = word.reversed()
        val words = setOf(word + word, word + rev, rev + word, rev + rev)
        val trail = Trail(listOf(0 to 0, 1 to 1, 2 to 2, 0 to 2, 1 to 1, 2 to 0).map { Point(it.first, it.second) })
        return input.search(words, listOf(trail))
    }
}
