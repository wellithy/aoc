package com.arxict.aoc.year2015


class Day10(lines: List<String>) {
    val start = lines.first()
    val first = lines[1].toInt()
    val second = lines.last().toInt()

    companion object {

        fun Iterator<Char>.next(char: Char): Pair<Int, Char?> {
            var count = 1
            while (hasNext()) next().let {
                if (it == char) count++
                else return count to it
            }
            return count to null
        }

        fun lookAndSay(seq: Sequence<Char>): Sequence<Char> = object : Iterator<String> {
            val itr = seq.iterator()
            var next = itr.takeIf { it.hasNext() }?.next()
            override fun hasNext(): Boolean = next != null
            override fun next(): String =
                next?.let { prev ->
                    itr.next(prev).let { (count, nxt) ->
                        next = nxt
                        "$count$prev"
                    }
                } ?: throw NoSuchElementException()
        }.asSequence().flatMap(String::asSequence)
    }

    fun count(count: Int) =
        generateSequence(start.asSequence(), ::lookAndSay).take(count + 1).last().count()

    fun part1() = count(first)
    fun part2() = count(second)
}
