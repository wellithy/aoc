package com.arxict.aoc

import com.arxict.aoc.common.Point
import com.arxict.aoc.year2024.*
import kotlin.io.path.createTempFile
import kotlin.io.path.writeLines
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class Year2024Test : BaseYearTest("2024"){
    @Ignore
    @Test
    fun day18Part2() {
        assertEquals(Point(6, 1), Day18(example("Day18.txt"), 6).part2())
    }

    @Test
    fun day18() {
        assertEquals(22, Day18(example("Day18.txt"), 6).part1(12))
        assertEquals(310, Day18(puzzle("Day18.txt"), 70).part1(1024))
    }

    @Ignore
    @Test
    fun day17() {
        Day17(puzzle("Day17.txt")).part2()
    }

    @Test
    fun day17Part1AndPart2Test() {
        assertEquals("4,6,3,5,6,3,5,2,1,0", Day17(example("Day17.txt")).part1())
        assertEquals("3,1,5,3,7,4,2,7,5", Day17(puzzle("Day17.txt")).part1())
        assertEquals(117440L, Day17(example("Day17-2.txt")).part2())
    }

    @Test
    fun day16Part1Only() {
        assertEquals(7036, Day16(example("Day16.txt")).part1())
        assertEquals(11048, Day16(example("Day16-2.txt")).part1())
        assertEquals(98520, Day16(puzzle("Day16.txt")).part1())
    }

    @Test
    fun day15() {
        val puzzle = puzzle("Day15.txt")
        assertEquals(1511865, Day15(puzzle, false).solve())
        assertEquals(1519991, Day15(puzzle, true).solve())

        val example = example("Day15.txt")
        assertEquals(10092, Day15(example, false).solve())
        assertEquals(9021, Day15(example, true).solve())

        assertEquals(2028, Day15(example("Day15-small.txt"), false).solve())
        Day15(example("Day15-small-part2.txt"), true).solve()
    }

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
}
