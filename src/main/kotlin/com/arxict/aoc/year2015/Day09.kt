package com.arxict.aoc.year2015

import com.arxict.aoc.common.*

class Day09(lines: List<String>) {
    val graph = buildGraph {
        val regex = Regex("""(.+) to (.+) = (\d+)""")
        lines.forEach { line ->
            regex.find(line)!!.groupValues.let { (_, from, to, distance) ->
                distance.toInt().let { add(from, to, it, it) }
            }
        }
    }.let(::AdditiveGraph)

    fun solve(): String = with(graph) {
        val cycle = hamiltonianCycle
        return graph.keys.asSequence().flatMap(cycle::solve).map(::cost).minMax().let { (part1, part2) ->
            "$part1 $part2"
        }
    }
}