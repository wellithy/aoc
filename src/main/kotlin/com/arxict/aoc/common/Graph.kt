package com.arxict.aoc.common

import java.util.*

fun <V> dijkstra(
    neighbors: (V) -> Sequence<V>,
    start: V,
    end: V,
    comparator: Comparator<List<V>>,
): List<V>?  {
    val nullLast = nullsLast(comparator)
    operator fun List<V>.compareTo(that: List<V>?): Int =
        nullLast.compare(this, that)

    val pq = PriorityQueue<List<V>>(comparator)
    val map = mutableMapOf<V, List<V>>()

    fun examine(vertex: V, next: List<V>) {
        if (next < map[vertex]) {
            pq += next
            map[vertex] = next
        }
    }

    examine(start, listOf(start))

    val visited = mutableSetOf<V>()
    while (pq.isNotEmpty())
        pq.remove().last().takeUnless { it in visited }?.let { tip ->
            visited += tip
            val path = map.getValue(tip)
            neighbors(tip).forEach { vertex ->
                val next = path + vertex
                if(vertex == end)
                    return next
                else if (vertex !in visited)
                    examine(vertex, next)
            }
        }
    return null
}

