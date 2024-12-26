package com.arxict.aoc.year2024

import com.arxict.aoc.AocTest
import com.arxict.aoc.common.parse
import com.arxict.aoc.common.plot
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
            println(steps) // 8149
            val plot = points.plot(rows, columns)
            val temp = createTempFile()
            println(temp) // moved to "Day14-tree.txt"
            temp.writeLines(plot)
        }
    }

    @Test
    fun validatePart2() = with(day14()) {
        val saved = AocTest.puzzle(2024, "Day14-tree.txt")
        val points = saved.parse(rows, columns)
        assertEquals(8149 to points, actualPart2())
    }
}
