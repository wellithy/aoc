package com.arxict.aoc.year2024

import com.arxict.aoc.AocTest
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class Day15Test {
    @Test
    fun day15_small() = assertEquals(2028, Day15(AocTest.example(2024, "Day15-small.txt")).part1())

    @Test
    fun day15_small_part2() {
        Day15(AocTest.example(2024, "Day15-small-part2.txt")).part2()
    }

}
