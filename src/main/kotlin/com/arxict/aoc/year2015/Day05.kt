package com.arxict.aoc.year2015
class Day05(val lines: List<String>) {

    val needed = "aeiou"
    val bad = listOf("ab", "cd", "pq", "xy")
    fun String.nice(): Boolean {
        var vowels = 0
        var twice = false
        forEachIndexed { i, c ->
            if (c in needed) vowels++
            getOrNull(i + 1)?.let {
                if (c == it) twice = true
                if ("$c$it" in bad) return false
            }
        }
        return twice && vowels >= 3
    }

    fun part1(): Int =
        lines.count { it.nice() }

    fun String.nice2(): Boolean {
        buildMap<String, MutableList<Int>> {
            (0..<lastIndex).forEach { index ->
                val list = getOrPut(substring(index, index + 2), ::mutableListOf)
                list.firstOrNull { it != index - 1 }?.let { return@buildMap }
                list += index
            }
            return false
        }
        return windowed(3).any { it.first() == it.last() }
    }

    fun part2(): Int =
        lines.count { it.nice2() }

}
