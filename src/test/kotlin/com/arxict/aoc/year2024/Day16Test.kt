package com.arxict.aoc.year2024

import com.arxict.aoc.AocTest
import junit.framework.TestCase.assertEquals
import kotlin.test.Ignore
import kotlin.test.Test

class Day16Test {
    @Test
    fun day16Part1Only() {
        assertEquals(11048, Day16(AocTest.example(2024, "Day16-2.txt")).part1())
    }
}
