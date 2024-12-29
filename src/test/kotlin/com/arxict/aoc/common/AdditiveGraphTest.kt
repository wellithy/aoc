package com.arxict.aoc.common

import org.junit.Test
import kotlin.test.assertEquals

class AdditiveGraphTest {

    @Test
    fun cycleCost() {
        val graph = buildGraph {
            add(1, 2, 100)
            add(2, 3, 300)
            add(1, 3, 200)
            add(3, 2, 400)
        }.let(::AdditiveGraph)
        graph.hamiltonianCycle.solve(1).map { graph.cost(it) }.minMax().let { assertEquals(400 to 600, it) }
    }
}