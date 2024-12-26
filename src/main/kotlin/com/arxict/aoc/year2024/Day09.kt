package com.arxict.aoc.year2024

class Day09(val lines: List<String>) {
    fun expand(line: String) = buildList {
        var id = 0
        line.forEachIndexed { i, c ->
            val s = if (i % 2 == 0) id++ else -1
            repeat(c.digitToInt()) { add(s) }
        }
    }

    fun free(orig: List<Int>) = buildList<Int> {
        addAll(orig)
        var right = lastIndex
        var left = 0
        while (left < right) {
            if (this[left] >= 0) {
                left++
                continue
            }
            if (this[right] < 0) {
                right--
                continue
            }
            this[left] = this[right]
            this[right] = -1
            left++
            right--
        }
    }

    fun whole(orig: List<Int>) = buildList<Int> {
        addAll(orig)
        var right = generateSequence(lastIndex, Int::dec).first { this[it] >= 0 }
        var id = this[right]
        while (id > 0) {
            right = generateSequence(right, Int::dec).first { this[it] == id }
            val block = generateSequence(right, Int::dec).first { this[it] != id }
            var left = (0..block).firstOrNull { this[it] < 0 } ?: break
            while (true) {
                val end = (left.inc()..block).firstOrNull { this[it] >= 0 } ?: block.inc()
                if (right - block <= end - left) {
                    repeat(right - block) {
                        this[left + it] = id
                        this[right - it] = -1
                    }
                    break
                } else left = (end..block).firstOrNull { this[it] < 0 } ?: break
            }
            right = block
            id--
        }
    }

    fun checksum(list: List<Int>): Long =
        list.foldIndexed(0L) { index, checksum, id -> if (id < 0) checksum else checksum + index * id }

    fun solve(): String {
        val list = expand(lines.first())
        val part1 = checksum(free(list))
        val part2 = checksum(whole(list))
        return "$part1 $part2"
    }
}
