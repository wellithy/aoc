package com.arxict.aoc.year2024

import com.arxict.aoc.common.Point
import com.arxict.aoc.common.dijkstra
import com.arxict.aoc.common.integers
import com.arxict.aoc.common.neighbors

class Day18(lines: List<String>) {
    val max = lines[0].toInt()
    val steps = lines[1].toInt()
    val corrupted = lines.subList(2, lines.size).map { it.integers().let { Point(it[0], it[1]) } }
    val validRange = 0..max
    fun Point.valid(step: Int) = row in validRange && column in validRange && corrupted.indexOf(this) !in 0..<step
    val start = Point(0, 0)
    val end = Point(max, max)
    fun Point.neighbors(step: Int) = neighbors().filter { it.valid(step) }

    fun List<Point>.cost(): Int =
        lastIndex

    fun find(places:Int): Int? =
        dijkstra({ it.neighbors(places) }, start, end, compareBy { it.cost() })?.cost()

    fun part1(): Int? = find(steps)

    fun part2() = "?"

    fun _part2(): Point =
        (0..corrupted.lastIndex).first { find(it) == null }.let{corrupted[it-1]}

}
