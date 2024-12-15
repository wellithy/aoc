package com.arxict.aoc.year2024

import com.arxict.aoc.common.integers

class Day05(val lines: List<String>) {

    fun solve() = with(lines.iterator()) {
        val rule = buildMap {
            while (true)
                next().takeIf(String::isNotEmpty)?.integers()?.let { (before, after) ->
                    getOrPut(before, ::mutableSetOf) += after
                } ?: break
        }

        var part1 = 0
        var part2 = 0
        while (hasNext()) {
            val pages = next().integers()
            val sorted = pages.sortedWith { a, b ->
                rule[a]?.takeIf { b in it }?.let { -1 } ?: 1
            }
            val mid = sorted[pages.size / 2]
            if (pages == sorted) part1 += mid
            else part2 += mid
        }
        part1 to part2
    }
}
