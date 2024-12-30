package com.arxict.aoc.common


class AdditiveGraph<V, E>(val graph: Graph<V, E>, val adderComparator: AdderComparator<E>)

fun <V> AdditiveGraph(graph: Graph<V, Int>): AdditiveGraph<V, Int> =
    AdditiveGraph(graph, intAdderComparator)

fun <V, E> AdditiveGraph<V, E>.cost(path: List<V>, closed: Boolean = false): E =
    graph.edges(path).reduce(adderComparator::add)
        .let { if (closed) adderComparator.add(it, graph.edge(path.last(), path.first())) else it }

val <V, E> AdditiveGraph<V, E>.pathComparator: Comparator<List<V>>
    get() = compareBy(adderComparator, ::cost)

val <V, E> AdditiveGraph<V, E>.closedPathComparator: Comparator<List<V>>
    get() = compareBy(adderComparator) { cost(it, true) }

fun <V, E> AdditiveGraph<V, E>.travelingSalesmanProblem(): Pair<E, E> =
    with(graph) {
        hamiltonianCycle.solve(map.keys.first()).map { cost(it, true) }.minMaxWith(adderComparator)
    }

fun <V, E> AdditiveGraph<V, E>.dijkstra(start: V, end: V): List<V>? =
    with(graph) {
        dijkstra({ map.getValue(it).keys.asSequence() }, start, end, pathComparator)
    }
