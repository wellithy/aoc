package com.arxict.aoc

import com.arxict.aoc.common.LinearEquationSolver
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

private typealias TL = Triple<Long, Long, Long>

class LinearEquationSolverTest {
    @Test
    fun simple() {
        assertEquals(
            1L to 4L,
            LinearEquationSolver.whole(
                Triple(1, 1, 5), Triple(3, 1, 7)
            )
        )
    }

    private fun assertSame(expected: TL, actual: TL) {
        fun validate(x: TL.() -> Long) = assertTrue(actual.third * expected.x() == expected.third * actual.x())
        validate(TL::first)
        validate(TL::second)
    }

    @Test
    fun fraction() {
        assertSame(
            Triple(1L, 2L, 3L),
            LinearEquationSolver.solve(Triple(3, 3, 3), Triple(6, 3, 4))
        )
        assertNull(LinearEquationSolver.whole(Triple(3, 3, 3), Triple(6, 3, 4)))
    }


}