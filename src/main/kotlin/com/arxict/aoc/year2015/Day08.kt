package com.arxict.aoc.year2015

class Day08(val lines: List<String>) {
    companion object {
        const val ESCAPE = '\\'
        const val DOUBLE_QUOTE = '"'
        const val START_HEX = 'x'
        val escaped = setOf(ESCAPE, DOUBLE_QUOTE, START_HEX)
        fun String.unescape(): Int {
            var diff = 2
            var index = 0
            while (++index < lastIndex) if (this[index] == ESCAPE) {
                diff++
                if (this[++index] == START_HEX) {
                    diff += 2
                    index += 2
                }
            }
            return diff
        }

        fun String.escape(): Int =
            2 + count { it == DOUBLE_QUOTE } + count { it == ESCAPE }
    }

    fun solve(): Pair<Int, Int> {
        return lines.sumOf { it.unescape() } to lines.sumOf { it.escape() }
    }
}
