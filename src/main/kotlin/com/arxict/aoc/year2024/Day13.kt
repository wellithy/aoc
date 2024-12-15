package com.arxict.aoc.year2024

import com.arxict.aoc.common.LinearEquationSolver
import com.arxict.aoc.common.Point

class Day13(lines: List<String>) {
    val input = lines.windowed(3, 4).map(::Claw)

    fun part1(): Long = input.mapNotNull { it.solve() }.sum()
    fun part2(): Long = input.mapNotNull { it.solve(10_000_000_000_000) }.sum()

    fun Claw.solve(shift: Long = 0): Long? =
        LinearEquationSolver.whole(
            Triple(a.move.row.toLong(), b.move.row.toLong(), prize.row + shift),
            Triple(a.move.column.toLong(), b.move.column.toLong(), prize.column + shift)
        )?.let { (aMoves, bMoves) ->
            aMoves * a.tokens + bMoves * b.tokens
        }

    fun solve(): Pair<Long, Long> =
        part1() to part2()

    companion object {
        data class Button(val type: Char, val move: Point)

        val Button.tokens: Int
            get() = when (type) {
                'A' -> 3
                'B' -> 1
                else -> error("Unknown type")
            }

        val buttonRegex = Regex("""Button (.): X\+(\d+), Y\+(\d+)""")
        fun point(result: MatchResult, index: Int): Point =
            Point(result.groupValues[index].toInt(), result.groupValues[index.inc()].toInt())

        fun Button(line: String): Button =
            buttonRegex.find(line)?.let {
                Button(it.groupValues[1].single(), point(it, 2))
            } ?: error("Wrong button line: $line")

        data class Claw(val a: Button, val b: Button, val prize: Point)

        val prizeRegex = Regex("""Prize: X=(\d+), Y=(\d+)""")
        fun Claw(lines: List<String>): Claw =
            Claw(Button(lines[0]), Button(lines[1]), point(prizeRegex.find(lines[2])!!, 1))
    }

}
