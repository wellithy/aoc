package com.arxict.aoc.year2024

import com.arxict.aoc.common.Point
import com.arxict.aoc.common.dijkstra
import com.arxict.aoc.common.integers
import com.arxict.aoc.common.neighbors

class Day18(lines: List<String>, val max: Int) {
    val corrupted = lines.map { it.integers().let { Point(it[0], it[1]) } }
    val validRange = 0..max
    fun Point.valid(step: Int) = row in validRange && column in validRange && corrupted.indexOf(this) !in 0..<step
    val start = Point(0, 0)
    val end = Point(max, max)
    fun Point.neighbors(step: Int) = neighbors().filter { it.valid(step) }

    fun List<Point>.cost(): Int =
        lastIndex

    fun part1(steps: Int): Int? =
        dijkstra({ it.neighbors(steps) }, start, end, compareBy { it.cost() })?.cost()

    fun part2(): Point =
        (0..corrupted.lastIndex).first { part1(it) == null }.let{corrupted[it-1]}

}
