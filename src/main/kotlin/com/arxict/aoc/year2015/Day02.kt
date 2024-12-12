package com.arxict.aoc.year2015
class Day02(lines: List<String>) {
    val input = lines.map { it.split('x').map(String::toInt) }

    fun List<Int>.area(): Int {
        val a = get(0) * get(1)
        val b = get(1) * get(2)
        val c = get(2) * get(0)
        return 2 * (a + b + c) + a.coerceAtMost(b.coerceAtMost(c))
    }

    fun part1(): Int =
        input.sumOf { it.area() }

    fun List<Int>.wrap(): Int {
        val a = get(0) + get(1)
        val b = get(1) + get(2)
        val c = get(2) + get(0)
        return 2 * a.coerceAtMost(b.coerceAtMost(c)) + get(0) * get(1) * get(2)
    }

    fun part2(): Int =
        input.sumOf { it.wrap() }
}
