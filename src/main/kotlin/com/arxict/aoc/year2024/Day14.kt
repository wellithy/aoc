package com.arxict.aoc.year2024

import com.arxict.aoc.common.*


class Day14(lines: List<String>, val rows: Int, val columns: Int) {
    val input = lines.map(::Robot)
    fun List<Point>.picture(): List<String> =
        MutableList<MutableList<Char>>(rows) { MutableList(columns) { ' ' } }.let { canvas ->
            forEach { canvas[it.row][it.column] = 'O' }
            canvas.map { it.joinToString("") }
        }

    fun part1(): Int =
        input.map { it.step(100) }
            .groupingBy { it.quadrant() }
            .eachCount()
            .map { (k, v) -> k?.let { v } ?: 1 }
            .reduce(Int::times)


    fun List<Point>.hasLine(len: Int) =
        any { it.segment(Direction.Right, len).all(::contains) }

    fun part2(): Pair<Int, List<String>> {
        for (s in 0..1_000_000)
            with(input.map { it.step(s) }) {
                if (hasLine(rows / 4))
                    return s to picture()
            }
        error("Can;t find")
    }

    fun sym(x: Int, xx: Int) = if (x < xx / 2) 'a' else 'b'
    fun Point.quadrant(): String? =
        if (row == rows / 2 || column == columns / 2) null
        else "${sym(row, rows)}${sym(column, columns)}"

    fun Robot.step(seconds: Int): Point =
        (position + velocity * seconds).let {
            Point(Math.floorMod(it.row, rows), Math.floorMod(it.column, columns))
        }

    companion object {
        data class Robot(var position: Point, val velocity: Point)

        // p=0,4 v=3,-3
        fun Point(str: String): Point =
            str.substring(2).split(',').let { Point(it[0].toInt(), it[1].toInt()) }

        fun Robot(line: String): Robot =
            line.split(' ').let { Robot(Point(it[0]), Point(it[1])) }

    }

}
