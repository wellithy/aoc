package com.arxict.aoc

import com.arxict.aoc.common.readLines
import com.arxict.aoc.year2024.*
import org.junit.Test
import kotlin.io.path.Path
import kotlin.streams.asSequence
import kotlin.test.assertEquals

class Year2024Test {
    @Test
    fun day11() {
        assertEquals(55312L, Day11(example("Day11.txt")).blink(25))
        val main = Day11(puzzle("Day11.txt"))
        assertEquals(199982L, main.blink(25))
        assertEquals(237149922829154L, main.blink(75))
    }

    @Test
    fun day10() {
        assertEquals(36 to 81, Day10(example("Day10.txt")).solve())
        assertEquals(607 to 1384, Day10(puzzle("Day10.txt")).solve())
    }

    @Test
    fun day09() {
        assertEquals(1928L to 2858L, Day09(example("Day09.txt")).solve())
        assertEquals(6384282079460 to 6408966547049, Day09(puzzle("Day09.txt")).solve())
    }

    @Test
    fun day08() {
        assertEquals(14 to 34, Day08(example("Day08.txt")).solve())
        assertEquals(285 to 944, Day08(puzzle("Day08.txt")).solve())
    }

    @Test
    fun day07() {
        assertEquals(3749L to 11387L, Day07(example("Day07.txt")).solve())
        assertEquals(66343330034722L to 637696070419031L, Day07(puzzle("Day07.txt")).solve())
    }

    @Test
    fun day06() {
        assertEquals(41 to 6, Day06(example("Day06.txt")).solve())
        assertEquals(4580 to 1480, Day06(puzzle("Day06.txt")).solve())
    }

    @Test
    fun day05() {
        val main = Day05(puzzle("Day05.txt"))
        assertEquals(4135 to 5285, main.solve())
    }

    @Test
    fun day04() {
        val main = Day04(puzzle("Day04.txt"))
        assertEquals(2447, main.part1())
        assertEquals(1868, main.part2())
    }

    @Test
    fun day03() {
        val main = Day03(puzzle("Day03.txt"))
        assertEquals(173419328, main.part1())
        assertEquals(90669332, main.part2())
    }

    @Test
    fun day02() {
        val main = Day02(puzzle("Day02.txt"))
        assertEquals(624, main.part1())
        assertEquals(658, main.part2())
    }

    @Test
    fun day01() {
        val main = Day01(puzzle("Day01.txt"))
        assertEquals(2166959, main.part1())
        assertEquals(23741109, main.part2())
    }

    private val example = Path("2024")
    private val puzzle = example.resolve("__aoc_my_puzzle_input__")
    fun puzzle(name: String) = puzzle.resolve(name).readLines()
    fun example(name: String) = example.resolve(name).readLines()
}
