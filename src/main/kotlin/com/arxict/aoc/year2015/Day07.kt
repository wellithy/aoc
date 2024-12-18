package com.arxict.aoc.year2015

class Day07(val lines: List<String>) {

    fun part1(): Int {
        val signal = mutableMapOf<String, UShort>()
        val directRegEx = Regex("""(\d+) -> (.+)""")
        var remaining = mutableListOf<String>()
        lines.forEach {line ->
            directRegEx.find(line)?.groupValues?.let {(_, value, id) ->
                signal[id] = value.toUShort()
            } ?: run { remaining += line }
        }
        println(remaining.size)

        return signal.getValue("a").toInt()
    }

    fun part2(): Int {
        return 0
    }

    companion object {

    }


}
