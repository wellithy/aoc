package com.arxict.aoc.year2024

import com.arxict.aoc.AocTest
import junit.framework.TestCase.assertEquals
import kotlin.test.Ignore
import kotlin.test.Test

class Day17Test {
    @Ignore
    @Test
    fun day17() {
        Day17(AocTest.puzzle(2024, "Day17.txt")).part2()
    }

    @Test
    fun day17Part1AndPart2Test() {
        assertEquals(117440L, Day17(AocTest.example(2024, "Day17-2.txt"))._part2())
    }
}
