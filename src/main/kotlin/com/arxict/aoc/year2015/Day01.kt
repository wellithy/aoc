package com.arxict.aoc.year2015

class Day01(lines: List<String>) {
    val input = lines.first()

    val UP = '('
    fun part1(): Int =
        input.fold(0) { f, c -> if (c == UP) f.inc() else f.dec() }

    fun part2(): Int {
        var floor = 0
        input.forEachIndexed { i, c ->
            if (c == UP) floor++
            else if (--floor < 0) return i.inc()
        }
        error("not found")
    }
}
