package com.arxict.aoc.year2015

class Day03(lines: List<String>) {
    val input = lines.first()

    data class House(val x: Int, val y: Int)

    fun House.move(c: Char): House {
        var a = x
        var b = y
        when (c) {
            '>' -> a++
            '<' -> a--
            '^' -> b++
            'v' -> b--
            else -> error("unknown $c")
        }
        return House(a, b)
    }

    fun MutableSet<House>.add(input: String, range: IntProgression) = apply {
        range.fold(House(0, 0).also(::add)) { h, i -> h.move(input[i]).also(::add) }
    }

    fun part1(): Int = buildSet {
        add(input, input.indices)
    }.size

    fun part2(): Int = buildSet {
        add(input, 1..<input.length step 2)
        add(input, 0..<input.length step 2)
    }.size

}
