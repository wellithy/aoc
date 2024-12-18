package com.arxict.aoc.year2024

import com.arxict.aoc.common.integers
import com.arxict.aoc.common.remove
import kotlin.math.absoluteValue

class Day02(lines: List<String>) {
    val input = lines.map(String::integers)

    fun List<Int>.safe(): Int? {
        val increase = first() < last()
        return asSequence().windowed(2) { it[1] - it[0] }.withIndex().firstOrNull { (_, delta) ->
            delta.absoluteValue !in 1..3 || ((delta > 0) xor increase)
        }?.index
    }

    fun part1(): Int =
        input.count { it.safe() == null }

    fun List<Int>.safeWithDamper(): Int? =
        safe()?.let {
            remove(it.inc()).safe()?.let {
                remove(it).safe()
            }
        }

    fun part2(): Int =
        input.count { it.safeWithDamper() == null }

}
