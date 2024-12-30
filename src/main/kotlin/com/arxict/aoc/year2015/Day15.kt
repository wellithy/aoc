package com.arxict.aoc.year2015

import com.arxict.aoc.common.*

class Day15(lines: List<String>) {
    val ingredients = lines.map {
        val regex = Regex("""[^-\d]+(-?\d+)""".repeat(5))
        regex.find(it)!!.groupValues.let {
            it.subList(1, it.size).map(String::toInt)
        }
    }.let(::Matrix)

    val total = 100
    val calories = 500
    fun eval(minusOne: List<Int>): Pair<Int, Int>? =
        ((minusOne + (total - minusOne.sum())) * ingredients).let {
            it.subList(0, it.lastIndex).fold(1) { m, p ->
                if (p <= 0) return null
                m * p
            } to it.last()
        }

    fun solve(): String {
        var part1 = Int.MIN_VALUE
        var part2 = Int.MIN_VALUE
        Permutation.withReplace(ingredients.rows - 1, total).mapNotNull(::eval).forEach {
            part1 = part1.coerceAtLeast(it.first)
            if (it.second == calories)
                part2 = part2.coerceAtLeast(it.first)
        }
        return "$part1 $part2"
    }
}
