package com.arxict.aoc.twenty24

import com.arxict.aoc.test.input
import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {
    @Test
    fun puzzle() {
        val main = Day01(input())
        assertEquals(2166959, main.part1())
        assertEquals(23741109, main.part2())
    }

}