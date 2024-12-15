package com.arxict.aoc

import com.arxict.aoc.common.Point
import com.arxict.aoc.common.readLines
import com.arxict.aoc.year2024.*
import org.junit.Test
import kotlin.io.path.Path
import kotlin.io.path.createTempFile
import kotlin.io.path.writeLines
import kotlin.test.assertEquals

class Year2024Test {
    @Test
    fun day14() {
        assertEquals(12, Day14(example("Day14.txt"), 11, 7).part1())
        val day14 = Day14(puzzle("Day14.txt"), 101, 103)
        assertEquals(214400550, day14.part1())

        val space = ' '
        val mark = 'O'
        fun Day14.picture(points: Set<Point>): List<String> =
            List<MutableList<Char>>(rows) { MutableList(columns) { space } }.let { canvas ->
                points.forEach { canvas[it.row][it.column] = mark }
                canvas.map { it.joinToString("") }
            }
        if (false)
            day14.part2()?.second?.let {
                createTempFile().also(::println).writeLines(day14.picture(it))
            }

        fun Day14.parse(lines: List<String>): Set<Point> = buildSet {
            require(lines.size == rows)
            lines.forEachIndexed { row, line ->
                require(line.length == columns)
                line.forEachIndexed { column, c ->
                    if (c == mark) add(Point(row, column))
                    else require(c == space)
                }
            }
        }
        assertEquals(8149 to day14.parse(puzzle("Day14-tree.txt")), day14.part2())
    }

    @Test
    fun day13() {
        assertEquals(480L to 875_318_608_908, Day13(example("Day13.txt")).solve())
        assertEquals(33481L to 92_572_057_880_885, Day13(puzzle("Day13.txt")).solve())
    }

    @Test
    fun day12() {
        assertEquals(1549354 to 937032, Day12(puzzle("Day12.txt")).solve())
        assertEquals(1930 to 1206, Day12(example("Day12.txt")).solve())
        assertEquals(8, Day12(example("Day12-1.txt")).sides('C'))
        with(Day12(example("Day12-2.txt"))) {
            assertEquals(4, sides('X'))
            assertEquals(4, sides(Point(3, 1)))
            assertEquals(12, sides('E'))
        }
        assertEquals(4, Day12.contiguous(listOf(1, 2, 3, 4, 9, 10, 11, 20, 22)))
    }

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
