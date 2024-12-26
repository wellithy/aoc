package com.arxict.aoc.year2024

import com.arxict.aoc.common.integers

class Day07(val lines: List<String>) {
    class Line(val result: Long, val operands: List<Int>)

    fun Line(str: String) = str.split(':').let {
        Line(it[0].toLong(), it[1].integers())
    }

    fun combination(n: Int, m: Int) = object : Iterator<List<Int>> {
        var more = true
        val state = MutableList(n) { 0 }
        override fun hasNext() = more
        override fun next() =
            if (!more) throw NoSuchElementException()
            else state.toList().also {
                var index = 0
                while (more) {
                    if (++state[index] < m) break
                    state[index++] = 0
                    more = index < n
                }
            }
    }

    fun Line.evaluate(operators: List<Int>) = with(operands.iterator()) {
        operators.fold(next().toLong()) { result, operand ->
            val num = next()
            when (operand) {
                0 -> result + num
                1 -> result * num
                2 -> "$result$num".toLong()
                else -> error("BUG")
            }
        }
    }

    fun Line.valid(operators: Int) =
        combination(operands.size - 1, operators).asSequence().firstOrNull { evaluate(it) == result } != null

    fun solve(): String {
        val matrix = lines.map(::Line)
        val part1 = matrix.filter { it.valid(2) }.sumOf(Line::result)
        val part2 = matrix.filter { it.valid(3) }.sumOf(Line::result)
        return "$part1 $part2"
    }
}
