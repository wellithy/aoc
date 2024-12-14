package com.arxict.aoc.common

import kotlin.Long

class LinearEquationSolver {
    companion object {
        fun solve2x2(input: Matrix<Long>): Triple<Long, Long, Long> {
            require(input.rowSize == 3 && input.columnSize == 2)
            fun term(a: Int, b: Int, c: Int, d: Int): Long = input[a][b] * input[c][d]
            return Triple(
                term(0, 2, 1, 1) - term(1, 2, 0, 1),
                term(1, 2, 0, 0) - term(1, 0, 0, 2),
                term(0, 0, 1, 1) - term(0, 1, 1, 0),
            )
        }

        fun solve2x2whole(input: Matrix<Long>): Pair<Long, Long>? =
            with(solve2x2(input)) {
                if (third == 0L) return null
                if (first % third != 0L || second % third != 0L) return null
                return first / third to second / third
            }
    }
}