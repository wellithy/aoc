package com.arxict.aoc.year2024

import com.arxict.aoc.common.*

class Day12(lines: List<String>) {
    val input = Matrix(lines)

    @JvmInline
    value class Region(val plots: Set<Point>)

    operator fun Region.contains(point: Point): Boolean = point in plots
    operator fun Set<Region>.contains(point: Point): Boolean = any { point in it }
    val Region.plant get() = input[plots.first()]
    fun Region.area(): Int = plots.size
    fun Region.perimeter(): Int =
        4 * plots.size - plots.sumOf { input.getNotNull(it.neighbors()).count(plant::equals) }

    fun Region.part1(): Int = area() * perimeter()


    fun Region.sides(): Int {
        fun Point.c(d: Direction) = if (d.horizontal) column else row
        fun Point.r(d: Direction) = if (d.horizontal) row else column
        val contours = buildMap {
            plots.forEach { point ->
                Direction.entries.forEach { direction ->
                    if (point.neighbor(direction) !in plots)
                        getOrPut(direction, ::mutableMapOf)
                            .getOrPut(point.c(direction), ::mutableListOf) += point.r(direction)
                }
            }
        }
        return contours.flatMap { it.value.values }.sumOf(::contiguous)
    }

    fun Region.part2(): Int = area() * sides()

    fun Region(start: Point): Region = Region(buildSet {
        val plant = input[start]
        val queue = ArrayDeque<Point>().apply { add(start) }
        while (queue.isNotEmpty()) queue.removeFirst().let { test ->
            add(test)
            test.neighbors().forEach {
                if (input.getOrNull(it) == plant && add(it)) queue.add(it)
            }
        }
    })

    fun solve(): String =
        buildSet<Region> {
            input.points().forEach { start ->
                if (start !in this) add(Region(start))
            }
        }.let { it.fold(0) { s, r -> s + r.part1() } to it.fold(0) { s, r -> s + r.part2() } }
            .let { "${it.first} ${it.second}" }

    fun sides(point: Point) =
        Region(point).sides()

    fun sides(plant: Char) =
        sides(input.points().first { input[it] == plant })

    companion object {
        private fun List<Int>.indexOfNonContiguous(start: Int): Int? =
            subList(start, size).withIndex().firstOrNull { it.value != get(start) + it.index }?.let { start + it.index }

        fun contiguous(list: List<Int>): Int =
            with(list.sorted()) { generateSequence(0) { indexOfNonContiguous(it) }.count() }
    }
}
