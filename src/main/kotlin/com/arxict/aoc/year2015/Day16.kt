package com.arxict.aoc.year2015

import com.arxict.aoc.common.readResourceLines
import kotlin.io.path.Path

class Day16(val lines: List<String>) {
    val tape = readResourceLines(Path("2015", "Day16-tape.txt"))
        .map { it.split(": ") }
        .associateBy(List<String>::first) { it.last().toInt() }

    fun List<String>.part(): Boolean? {
        var one = true
        var two = true
        windowed(2, 2).forEach { (key, value) ->
            val num = value.toInt()
            val tap = tape.getValue(key)
            one = one && num == tap
            two = two && when (key) {
                "cats", "trees" -> num > tap
                "pomeranians", "goldfish" -> num < tap
                else -> num == tap
            }
            if (!one && !two) return null
        }
        return one
    }

    val regex = Regex("""Sue (\d+): (.+): (\d+), (.+): (\d+), (.+): (\d+)""")

    fun solve(): String {
        var part1 = ""
        var part2 = ""
        lines.forEach {
            regex.find(it)!!.groupValues.let { list ->
                list.subList(2, list.size).part()?.let {
                    if (it) part1 = list[1]
                    else part2 = list[1]
                    if (part1.isNotBlank() && part2.isNotBlank())
                        return "$part1 $part2"
                }
            }
        }
        error("Couldn't find")
    }
}
