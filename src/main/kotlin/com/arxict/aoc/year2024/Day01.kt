package com.arxict.aoc.year2024

import com.arxict.aoc.common.Matrix
import com.arxict.aoc.common.frequency
import com.arxict.aoc.common.integers
import com.arxict.aoc.common.transpose
import kotlin.math.absoluteValue

class Day01(lines: List<String>) {
    val input = Matrix(lines.map(String::integers)).transpose().rows

    infix fun Int.distance(other: Int): Int =
        minus(other).absoluteValue

    fun part1(): Int =
        input.map(List<Int>::sorted)
            .let { (first, second) ->
                first.indices.sumOf { first[it] distance second[it] }
            }

    fun part2(): Int =
        input.let { (first, second) ->
            val freq = second.frequency()
            first.sumOf { it * freq.getOrDefault(it, 0) }
        }
}