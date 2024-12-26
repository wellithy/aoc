package com.arxict.aoc.year2024

import com.arxict.aoc.common.longs

class Day11(lines: List<String>) {
    val input = lines.first().longs()

    fun blink(numbers: Map<Long, Long>) = mutableMapOf<Long, Long>().apply {
        numbers.forEach { (x, n) ->
            val str = x.toString()
            when {
                x == 0L -> merge(1L, n, Long::plus)
                str.length % 2 == 0 -> {
                    merge(str.substring(0, str.length / 2).toLong(), n, Long::plus)
                    merge(str.substring(str.length / 2).toLong(), n, Long::plus)
                }

                else -> merge(x * 2024, n, Long::plus)
            }
        }
    }

    fun blink(count: Int): Long {
        var map = mutableMapOf<Long, Long>().apply {
            input.forEach { put(it, 1L) }
        }
        repeat(count) {
            map = blink(map)
        }
        return map.values.sum()
    }

    fun part1() = blink(25)
    fun part2() = blink(75)
}
