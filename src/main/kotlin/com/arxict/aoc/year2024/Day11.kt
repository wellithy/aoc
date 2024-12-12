package com.arxict.aoc.year2024

import com.arxict.aoc.common.numbers

class Day11(lines: List<String>) {
    val input = lines.first().numbers().map(String::toLong)

    fun MutableMap<Long, Long>.update(key: Long, n: Long) =
        compute(key) { _, v -> n + (v ?: 0) }

    fun blink(numbers: Map<Long, Long>) = mutableMapOf<Long, Long>().apply {
        numbers.forEach { (x, n) ->
            val str = x.toString()
            when {
                x == 0L -> update(1L, n)
                str.length % 2 == 0 -> {
                    update(str.substring(0, str.length / 2).toLong(), n)
                    update(str.substring(str.length / 2).toLong(), n)
                }
                else -> update(x * 2024, n)
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
}
