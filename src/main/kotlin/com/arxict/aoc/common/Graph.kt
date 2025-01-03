package com.arxict.aoc.common

import java.util.*

@JvmInline
value class Graph<V, E>(val map: Map<V, Map<V, E>>)

class GraphBuilder<V, E> {
    private val data = mutableMapOf<V, MutableMap<V, E>>()
    fun build() = Graph(data)

    fun getOrPut(vertx: V): MutableMap<V, E> =
        data.getOrPut(vertx, ::mutableMapOf)

    fun add(v1: V, v2: V, from1to2: E, from2to1: E? = null) {
        getOrPut(v1)[v2.also(::getOrPut)] = from1to2
        from2to1?.let { data.getValue(v2)[v1] = it }
    }
}

fun <V, E> buildGraph(buildAction: GraphBuilder<V, E>.() -> Unit): Graph<V, E> =
    GraphBuilder<V, E>().apply(buildAction).build()

val <V> Graph<V, *>.hamiltonianCycle: PathBacktrack<V>
    get() = object : PathBacktrack<V>() {
        override fun accept(candidate: List<V>): Boolean = candidate.size == map.size
        override fun neighbors(vertex: V): Sequence<V> = map.getValue(vertex).keys.asSequence()
    }

fun <V, E> Graph<V, E>.edge(v1: V, v2: V): E =
    map.getValue(v1).getValue(v2)

fun <V, E> Graph<V, E>.edges(path: List<V>): Sequence<E> =
    path.asSequence().windowed(2).map { edge(it.first(), it.last()) }

fun <V> dijkstra(
    neighbors: (V) -> Sequence<V>,
    start: V,
    end: V,
    comparator: Comparator<List<V>>,
): List<V>? {
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
                if (vertex == end)
                    return next
                else if (vertex !in visited)
                    examine(vertex, next)
            }
        }
    return null
}
