package com.arxict.aoc

import com.arxict.aoc.common.LinearEquationSolver
import com.arxict.aoc.common.Matrix
import kotlin.test.Test
import kotlin.test.assertEquals

class LinearEquationSolverTest {
    @Test
    fun simple() {
        assertEquals(
            1L to 4L,
            LinearEquationSolver.solve2x2whole(
                Matrix(listOf(listOf(1, 1, 5), listOf(3, 1, 7)))
            )
        )
    }

}