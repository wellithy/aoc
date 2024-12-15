package com.arxict.aoc

import com.arxict.aoc.common.readLines
import com.arxict.aoc.year2015.*
import org.junit.Test
import kotlin.io.path.Path
import kotlin.test.assertEquals

class Year2015Test {
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

    private val example = Path("2015")
//    fun example(name: String) = example.resolve(name).readLines()
    private val puzzle = example.resolve("__aoc_my_puzzle_input__")
    fun puzzle(name: String) = puzzle.resolve(name).readLines()

}
