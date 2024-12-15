package com.arxict.aoc.year2015

import com.arxict.aoc.common.Direction
import com.arxict.aoc.common.Point
import com.arxict.aoc.common.neighbor

class Day06(val lines: List<String>, rows: Int = 1000, val columns: Int = 1000) {
    val instructions = lines.map(::Instruction)
    val lights = IntArray(rows * columns)

    val Point.index: Int get() = row * columns + column
    fun Point.apply(type: Type) = with(lights) {
        when (type) {
            Type.On -> lights[index] = 1
            Type.Off -> lights[index] = 0
            Type.Toggle -> lights[index] = 1 - lights[index]
        }
    }

    fun Instruction.apply() = points().forEach { it.apply(type) }

    fun part1(): Int {
        instructions.forEach { it.apply() }
        return lights.sum().also { lights.indices.forEach { lights[it] = 0 } }
    }

    fun Point.apply2(type: Type) = with(lights) {
        when (type) {
            Type.On -> lights[index]++
            Type.Off -> if(lights[index] > 0) lights[index]--
            Type.Toggle -> lights[index] += 2
        }
    }

    fun Instruction.apply2() = points().forEach { it.apply2(type) }
    fun part2(): Int {
        instructions.forEach { it.apply2() }
        return lights.sum()
    }

    companion object {
        enum class Type { On, Off, Toggle }

        fun Type(token: String): Type =
            when (token) {
                "turn on" -> Type.On
                "turn off" -> Type.Off
                "toggle" -> Type.Toggle
                else -> error("Unknown token $token")
            }

        data class Instruction(val type: Type, val topLeft: Point, val bottomRight: Point)

        val Instruction.width: Int get() = bottomRight.column - topLeft.column + 1
        val Instruction.height: Int get() = bottomRight.row - topLeft.row + 1

        fun Instruction.points(): Sequence<Point> =
            generateSequence(topLeft) { it.neighbor(Direction.Down) }.take(height)
                .flatMap { left -> generateSequence(left) { it.neighbor(Direction.Right) }.take(width) }

        val instructionRegex = Regex("""(.+?) (\d+),(\d+) through (\d+),(\d+)""")
        fun Instruction(line: String): Instruction =
            instructionRegex.find(line)!!.groupValues.let {
                val type = Type(it[1])
                it.subList(2, 6).map(String::toInt).let { (top, left, bottom, right) ->
                    Instruction(type, Point(top, left), Point(bottom, right))
                }
            }
    }


}
