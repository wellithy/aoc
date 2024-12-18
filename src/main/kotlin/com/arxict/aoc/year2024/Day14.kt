package com.arxict.aoc.year2024

import com.arxict.aoc.common.*
import java.lang.Math.floorMod

class Day14(lines: List<String>, val rows: Int, val columns: Int) {
    val input = lines.map(::Robot)

    fun part1(steps: Int = 100): Int =
        input.map { it.step(steps) }
            .groupingBy(::quadrant)
            .eachCount()
            .asSequence()
            .filterNot { it.key == null }
            .map(Map.Entry<*, Int>::value)
            .reduce(Int::times)

    fun List<Point>.hasLine(len: Int) =
        any { it.segment(Point.RIGHT, len).all(::contains) }


    fun part2(minLineLength: Int = xQuadrantLen / 2, maxTrials: Int = 1_000_000): Pair<Int, Set<Point>>? {
        for (s in 0..maxTrials)
            with(input.map { it.step(s) }) {
                if (hasLine(minLineLength))
                    return s to toSet()
            }
        return null
    }

    val xQuadrantLen = rows / 2
    val yQuadrantLen = columns / 2
    fun quadrant(point: Point): Int? = with(point) {
        if (row == xQuadrantLen || column == yQuadrantLen) null
        else (if (row < xQuadrantLen) 1 else 3) + if (column < yQuadrantLen) 0 else 1
    }

    fun Robot.step(seconds: Int): Point =
        (position + velocity * seconds).let {
            Point(floorMod(it.row, rows), floorMod(it.column, columns))
        }

    companion object {
        data class Robot(val position: Point, val velocity: Point)

        val robotRegex = Regex("""p=(\d+),(\d+) v=(-?\d+),(-?\d+)""")
        fun Robot(line: String): Robot =
            robotRegex.find(line)!!
                .groupValues
                .subList(1, 5)
                .map(String::toInt)
                .let { (px, py, vx, vy) ->
                    Robot(Point(px, py), Point(vx, vy))
                }
    }

}
