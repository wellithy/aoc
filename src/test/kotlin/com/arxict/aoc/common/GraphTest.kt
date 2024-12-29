package com.arxict.aoc.common

import kotlin.test.Test
import kotlin.test.assertEquals

class GraphTest {
    @Test
    fun simpleCycle() =
        buildUnweightedGraph {
            add(1, 2)
            add(2, 3)
            add(3, 4)
        }.hamiltonianCycle.solve(1).toList().let {
            assertEquals(listOf(1, 2, 3, 4), it.single())
        }

    @Test
    fun noCycle() =
        buildUnweightedGraph {
            add(1, 2)
            add(2, 3)
            add(5, 4)
        }.hamiltonianCycle.solve(1).count().let { assertEquals(0, it) }

    @Test
    fun multipleCycles() {
        buildUnweightedGraph {
            add(1, 2)
            add(2, 3)
            add(1, 3)
            add(3, 2)
        }.hamiltonianCycle.solve(1).toSet().let { assertEquals(setOf(listOf(1, 2, 3), listOf(1, 3, 2)), it) }
    }

}