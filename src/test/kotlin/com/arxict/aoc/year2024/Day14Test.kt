package com.arxict.aoc.year2024

import com.arxict.aoc.AocTest
import com.arxict.aoc.common.Matrix
import com.arxict.aoc.common.MutableMatrix
import com.arxict.aoc.common.asPoints
import com.arxict.aoc.common.lines
import kotlin.io.path.appendLines
import kotlin.io.path.createTempFile
import kotlin.io.path.writeLines
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {

    fun day14() = Day14(AocTest.puzzle(2024, "Day14.txt"))

    @Ignore
    @Test
    fun savePlot() {
        with(day14()) {
            val (steps, points) = actualPart2()!!
            val temp = createTempFile()
            temp.writeLines(listOf(steps.toString()))
            val plot = MutableMatrix(points.asSequence(), rows, columns)
            temp.appendLines(plot.lines())
            println("Saved to $temp")
        }
    }

    @Test
    fun validatePart2() = with(day14()) {
        val saved = AocTest.puzzle(2024, "Day14-tree.txt")
        val steps = saved[0].toInt()
        val points = Matrix(saved.subList(1, saved.size)).asPoints()
        assertEquals(steps to points.toSet(), actualPart2())
    }
}
