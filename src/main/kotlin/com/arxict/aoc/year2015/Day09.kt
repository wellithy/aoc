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
                getOrPut(from, ::mutableListOf) += to to num
                getOrPut(to, ::mutableListOf) += from to num
            }
        }
    }

    companion object {
    }

    fun cost(from: String, to: String) =
        graph.getValue(from).first { it.first == to }.second

    fun List<String>.cost() =
        windowed(2).sumOf { cost(it[0], it[1]) }

    inner class Travel : Backtrack<List<String>> {
        fun solve(): Sequence<List<String>> =
            graph.asSequence().flatMap { solve(listOf(it.key)) }

        override fun accept(candidate: List<String>): Boolean =
            (candidate.size == graph.size && candidate.containsAll(graph.keys))

        override fun first(candidate: List<String>): List<String>? =
            graph.getValue(candidate.last()).firstOrNull {
                it.first !in candidate
            }?.let { candidate + it.first }

        override fun next(candidate: List<String>): List<String>? {
            if (candidate.size < 2) return null
            val tip = candidate.last()
            val list = graph.getValue(candidate[candidate.size - 2])
            val index = list.indexOfFirst { it.first == tip }.also { (require(it >= 0)) }
            return list.subList(index.inc(), list.size).firstOrNull { it.first !in candidate }
                ?.let { candidate.replaceAt(it.first, candidate.lastIndex) }
        }
    }

    fun solve(): Pair<Int, Int> =
        Travel().solve().map { it.cost() }.minMax()
}
