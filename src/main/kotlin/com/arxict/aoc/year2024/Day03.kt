package com.arxict.aoc.year2024

class Day03(lines: List<String>) {
    val input = lines.joinToString("")

    val mul = Regex("""mul\((\d+),(\d+)\)""")
    fun part1(input: String): Int =
        mul.findAll(input).sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }

    fun part1(): Int =
        part1(input)

    val doNoDo = Regex("""do\(\)(.+?)don't\(\)""")
    fun part2(): Int =
        doNoDo.findAll("do()${input}don't()").sumOf { part1(it.groupValues[1]) }
}
