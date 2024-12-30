package com.arxict.aoc.year2015

import com.arxict.aoc.common.PathBacktrack

class Day17(lines: List<String>) {
    val capacities = lines.map(String::toInt)
    val total = 150

    var min = Int.MAX_VALUE
    var count = 0
    fun update(num: Int) {
        if (num == min) count++
        else if (num < min) {
            min = num
            count = 1
        }
    }

    val solver = object : PathBacktrack<Int>() {
        override fun neighbors(vertex: Int): Sequence<Int> =
            (vertex.inc()..capacities.lastIndex).asSequence()

        override fun accept(candidate: List<Int>): Boolean =
            (candidate.sumOf(capacities::get) == total).also {
                if (it) update(candidate.size)
            }
    }

    fun solve() = "${capacities.indices.asSequence().let(solver::solve).count()} $count"
}
