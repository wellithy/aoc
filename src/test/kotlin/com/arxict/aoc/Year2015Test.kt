package com.arxict.aoc

import com.arxict.aoc.year2015.*
import kotlin.test.Test
import kotlin.test.assertEquals

class Year2015Test : BaseYearTest("2015") {
    @Test
    fun day09() {
        assertEquals(605 to 982, Day09(example("Day09.txt")).solve())
        assertEquals(117 to 909, Day09(puzzle("Day09.txt")).solve())
    }

    @Test
    fun day08() {
        assertEquals(12 to 19, Day08(example("Day08.txt")).solve())
        assertEquals(1342 to 2074, Day08(puzzle("Day08.txt")).solve())
    }
    @Test
    fun day07() {
        assertEquals(3176 to 14710, Day07(puzzle("Day07.txt")).solve())
    }

    @Test
    fun day06() {
        val main = Day06(puzzle("Day06.txt"))
        assertEquals(569999, main.part1())
        assertEquals(17836115, main.part2())
    }

    @Test
    fun day05() {
        val main = Day05(puzzle("Day05.txt"))
        assertEquals(255, main.part1())
        assertEquals(55, main.part2())
    }

    @Test
    fun day04() {
        val main = Day04(puzzle("Day04.txt"))
        assertEquals(254575, main.solve("00000"))
        assertEquals(1038736, main.solve("000000"))
    }

    @Test
    fun day03() {
        val main = Day03(puzzle("Day03.txt"))
        assertEquals(2572, main.part1())
        assertEquals(2631, main.part2())
    }

    @Test
    fun day02() {
        val main = Day02(puzzle("Day02.txt"))
        assertEquals(1588178, main.part1())
        assertEquals(3783758, main.part2())
    }

    @Test
    fun day01() {
        val main = Day01(puzzle("Day01.txt"))
        assertEquals(280, main.part1())
        assertEquals(1797, main.part2())
    }

}
