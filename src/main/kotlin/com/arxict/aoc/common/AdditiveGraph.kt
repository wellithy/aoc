package com.arxict.aoc.common


class AdditiveGraph<V, E>(graph: Map<V, Map<V, E>>, val adderComparator: AdderComparator<E>) : Graph<V, E>(graph)

fun <V> AdditiveGraph(graph: Graph<V, Int>): AdditiveGraph<V, Int> =
    AdditiveGraph(graph.graph, intAdderComparator)

fun <V, E> AdditiveGraph<V, E>.cost(path: List<V>, closed: Boolean = false): E =
    edges(path).reduce(adderComparator::add)
        .let { if (closed) adderComparator.add(it, edge(path.last(), path.first())) else it }

val <V, E> AdditiveGraph<V, E>.pathComparator: Comparator<List<V>>
    get() = compareBy(adderComparator, ::cost)

val <V, E> AdditiveGraph<V, E>.closedPathComparator: Comparator<List<V>>
    get() = compareBy(adderComparator) { cost(it, true) }

fun <V, E> AdditiveGraph<V, E>.travelingSalesmanProblem(): Pair<E, E> =
    hamiltonianCycle.solve(graph.keys.first()).map { cost(it, true) }.minMaxWith(adderComparator)

fun <V, E> AdditiveGraph<V, E>.dijkstra(start: V, end: V): List<V>? =
    dijkstra({ graph.getValue(it).keys.asSequence() }, start, end, pathComparator)
