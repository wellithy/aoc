package com.arxict.aoc.year2015


class Day11(lines: List<String>) {
    val password = lines.first()

    fun increasing(str: String): Boolean = str[1] - str[0] == 1 && str[2] - str[1] == 1
    fun CharSequence.one(): Boolean = windowed(3).any(::increasing)

    fun CharSequence.three(): Boolean {
        var start = 0
        var count = 0
        while (++start < length)
            if (get(start) == get(start - 1)) {
                if (++count >= 2) return true
                start++
            }
        return false
    }

    fun CharSequence.valid(): Boolean = one() && three()
    fun Char.next(): Char = inc().let { if (it in "iol") it.inc() else it }

    fun StringBuilder.next() {
        var index = length
        while (this[--index] == 'z') set(index, 'a')
        set(index, this[index].next())
    }

    fun String.nextValid(): String = buildString {
        append(this@nextValid)
        do {
            next()
        } while (!valid())
    }

    fun part1() = password.nextValid()

    fun part2() = part1().nextValid()

    val minValidSequence = listOf(0, 0, 1, 2, 2)
    fun _part1() = StringBuilder(password).apply {
        val c = get(length - 5)
        minValidSequence.forEachIndexed { i, d -> set(length - 5 + i, c + d) }
    }.toString()
}
