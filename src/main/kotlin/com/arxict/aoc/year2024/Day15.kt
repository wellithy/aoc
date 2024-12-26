package com.arxict.aoc.year2024

import com.arxict.aoc.common.*
import com.arxict.aoc.common.Direction.*

class Day15(val lines: List<String>) {
    var matrix: MutableMatrix<Char>
    val directions: List<Direction>

    companion object {
        const val WALL = '#'
        const val BOX = 'O'
        const val ROBOT = '@'
        const val SPACE = '.'
        const val LEFT = '['
        const val RIGHT = ']'

        fun Iterator<String>.map() = mutableListOf<MutableList<Char>>().apply {
            while (true) {
                val line = next()
                if (line.isEmpty()) break
                add(line.toMutableList())
            }
        }.let(::MutableMatrix)

        fun Iterator<String>.directions() = buildList {
            while (hasNext()) next().forEach {
                when (it) {
                    '^' -> add(Up)
                    'v' -> add(Down)
                    '>' -> add(Right)
                    '<' -> add(Left)
                    else -> error("Unknown direction $it")
                }
            }
        }

        fun MutableMatrix<Char>.expand() =
            mutableRows.forEachIndexed { index, row ->
                mutableListOf<Char>().apply {
                    fun add2(c1: Char, c2: Char) = add(c1).also { add(c2) }
                    row.forEach {
                        when (it) {
                            WALL -> add2(WALL, WALL)
                            SPACE -> add2(SPACE, SPACE)
                            ROBOT -> add2(ROBOT, SPACE)
                            BOX -> add2(LEFT, RIGHT)
                        }
                    }
                }.let { mutableRows[index] = it }
            }
    }

    init {
        val itr = lines.iterator()
        matrix = itr.map()
        directions = itr.directions()
    }

    var robot = matrix.points().first { matrix[it] == ROBOT }

    fun moveTo(next: Point) {
        matrix[next] = ROBOT
        matrix[robot] = SPACE
        robot = next
    }

    fun horizontal(next: Point, direction: Direction) {
        val after = generateSequence(next) { it.neighbor(direction, 2) }.first { matrix[it] != matrix[next] }
        if (matrix[after] != SPACE) return
        var last = after
        while (last != next) {
            val prev = last.neighbor(-direction)
            matrix[last] = matrix[prev]
            last = prev
        }
        moveTo(next)
    }

    fun box(a: Point) = if (matrix[a] == LEFT) a to a.neighbor(Right) else a.neighbor(Left) to a

    fun Pair<Point, Point>.neighbor(direction: Direction) = first.neighbor(direction) to second.neighbor(direction)
    fun Pair<Point, Point>.items() = matrix[first] to matrix[second]

    fun canMove(next: Point, direction: Direction): Boolean {
        val box = box(next)
        val after = box.neighbor(direction)
        val items = after.items()
        return if (items.first == WALL || items.second == WALL) false
        else (items.first == SPACE || canMove(after.first, direction))
                && (items.second == SPACE || canMove(after.second, direction))
    }

    fun doMove(box: Pair<Point, Point>, direction: Direction) {
        while (true) {
            val after = box.neighbor(direction)
            if (after.items() == SPACE to SPACE) {
                matrix.swap(box.first, after.first)
                matrix.swap(box.second, after.second)
                return
            } else {
                if (matrix[after.first] != SPACE) doMove(box(after.first), direction)
                if (matrix[after.second] != SPACE) doMove(box(after.second), direction)
            }
        }
    }

    fun vertical(next: Point, direction: Direction) {
        if (!canMove(next, direction)) return
        doMove(box(next), direction)
        moveTo(next)
    }

    fun box(next: Point, direction: Direction) {
        val after = next.ray(direction.toPoint()).first { matrix[it] != BOX }
        if (matrix[after] != SPACE) return
        moveTo(next)
        matrix[after] = BOX
    }

    fun advance(direction: Direction) {
        val next = robot.neighbor(direction)
        when (matrix[next]) {
            SPACE -> moveTo(next)
            BOX -> box(next, direction)
            LEFT, RIGHT -> if (direction.horizontal) horizontal(next, direction) else vertical(next, direction)
        }
    }

    fun value(): Int =
        matrix.points().filter { matrix[it] == BOX || matrix[it] == LEFT }.sumOf { 100 * it.row + it.column }

    fun part1(): Int {
        directions.forEach {
            advance(it)
        }
        return value()
    }

    fun part2(): Int {
        val itr = lines.iterator()
        matrix = itr.map().apply { expand() }
        robot = matrix.points().first { matrix[it] == ROBOT }
        return part1()
    }

}

