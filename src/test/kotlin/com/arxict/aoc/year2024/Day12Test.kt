package com.arxict.aoc.year2024

import com.arxict.aoc.AocTest
import com.arxict.aoc.common.Point
import junit.framework.TestCase.assertEquals
import kotlin.test.Test

class Day12Test {

    @Test
    fun contiguous() = assertEquals(4, Day12.contiguous(listOf(1, 2, 3, 4, 9, 10, 11, 20, 22)))

    @Test
    fun day12_1() = assertEquals(8, Day12(AocTest.example(2024, "Day12-1.txt")).sides('C'))

    @Test
    fun day12_2() = with(Day12(AocTest.example(2024, "Day12-2.txt"))) {
        assertEquals(4, sides('X'))
        assertEquals(4, sides(Point(3, 1)))
        assertEquals(12, sides('E'))
    }
}
