package com.arxict.aoc.test

import com.arxict.aoc.common.readLines
import com.arxict.aoc.year2024.*
import org.junit.Test
import kotlin.io.path.Path
import kotlin.test.assertEquals

class Year2024Test {
    @Test
    fun day11() {
        assertEquals(55312L, Day11(example("11")).blink(25))
        val main = Day11(read("11"))
        assertEquals(199982L, main.blink(25))
        assertEquals(237149922829154L, main.blink(75))
    }

    @Test
    fun day10() {
        assertEquals(36 to 81, Day10(example("10")).solve())
        assertEquals(607 to 1384, Day10(read("10")).solve())
    }

    @Test
    fun day09() {
        assertEquals(1928L to 2858L, Day09(example("09")).solve())
        assertEquals(6384282079460 to 6408966547049, Day09(read("09")).solve())
    }

    @Test
    fun day08() {
        assertEquals(14 to 34, Day08(example("08")).solve())
        assertEquals(285 to 944, Day08(read("08")).solve())
    }

    @Test
    fun day07() {
        assertEquals(3749L to 11387L, Day07(example("07")).solve())
        assertEquals(66343330034722L to 637696070419031L, Day07(read("07")).solve())
    }

    @Test
    fun day06() {
        assertEquals(41 to 6, Day06(example("06")).solve())
        assertEquals(4580 to 1480, Day06(read("06")).solve())
    }

    @Test
    fun day05() {
        val main = Day05(read("05"))
        assertEquals(4135 to 5285, main.solve())
    }

    @Test
    fun day04() {
        val main = Day04(read("04"))
        assertEquals(2447, main.part1())
        assertEquals(1868, main.part2())
    }

    @Test
    fun day03() {
        val main = Day03(read("03"))
        assertEquals(173419328, main.part1())
        assertEquals(90669332, main.part2())
    }

    @Test
    fun day02() {
        val main = Day02(read("02"))
        assertEquals(624, main.part1())
        assertEquals(658, main.part2())
    }

    @Test
    fun day01() {
        val main = Day01(read("01"))
        assertEquals(2166959, main.part1())
        assertEquals(23741109, main.part2())
    }

    private fun read(day: String): List<String> =
        Path("2024", "__aoc_my_puzzle_input__", "Day$day.txt").readLines()

    private fun example(day: String): List<String> =
        Path("2024", "Day$day.txt").readLines()

}
