package com.arxict.aoc.year2024

class Day03(lines: List<String>) {
    val input = lines.joinToString("")

    val mul = Regex("""mul\((\d+),(\d+)\)""")
    fun partOne(input: String): Int =
        mul.findAll(input).sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }

    fun part1(): Int =
        partOne(input)

    val doNoDo = Regex("""do\(\)(.+?)don't\(\)""")
    fun part2(): Int =
        doNoDo.findAll("do()${input}don't()").sumOf { partOne(it.groupValues[1]) }
}
