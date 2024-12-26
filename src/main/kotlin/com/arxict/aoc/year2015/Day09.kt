package com.arxict.aoc.year2015

import com.arxict.aoc.common.Backtrack
import com.arxict.aoc.common.minMax
import com.arxict.aoc.common.replaceAt
import com.arxict.aoc.common.solve

class Day09(lines: List<String>) {
    val graph = buildMap {
        val regex = Regex("""(.+) to (.+) = (\d+)""")
        lines.forEach { line ->
            regex.find(line)!!.groupValues.let { (_, from, to, distance) ->
                val num = distance.toInt()
                getOrPut(from, ::LinkedHashMap)[to] = num
                getOrPut(to, ::LinkedHashMap)[from] = num
            }
        }
    }

    companion object {
    }

    fun List<String>.cost() =
        windowed(2).sumOf { (from, to) -> graph.getValue(from).getValue(to) }

    inner class Travel : Backtrack<List<String>> {
        fun solve(): Sequence<List<String>> =
            graph.asSequence().flatMap { solve(listOf(it.key)) }

        override fun accept(candidate: List<String>): Boolean =
            (candidate.size == graph.size && candidate.containsAll(graph.keys))

        override fun first(candidate: List<String>): List<String>? =
            graph.getValue(candidate.last()).keys.firstOrNull {
                it !in candidate
            }?.let { candidate + it }

        override fun next(candidate: List<String>): List<String>? {
            if (candidate.size < 2) return null
            val tip = candidate.last()
            val list = graph.getValue(candidate[candidate.size - 2]).keys.iterator()
            while (list.next() != tip) {
            }
            return list.asSequence().firstOrNull { it !in candidate }
                ?.let { candidate.replaceAt(it, candidate.lastIndex) }
        }
    }

    fun solve() =
        Travel().solve().map { it.cost() }.minMax().let { (part1, part2) ->
            "$part1 $part2"
        }
}
