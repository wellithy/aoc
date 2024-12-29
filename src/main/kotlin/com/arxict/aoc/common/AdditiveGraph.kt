package com.arxict.aoc.common

class AdditiveGraph<V, E>(graph: Map<V, Map<V, E>>, val adder: AdderComparator<E>) : Graph<V, E>(graph)

fun <V> AdditiveGraph(graph: Graph<V, Int>): AdditiveGraph<V, Int> =
    AdditiveGraph(graph.graph, intAdderComparator)

fun <V, E> AdditiveGraph<V, E>.cost(path: List<V>, closed: Boolean = false): E =
    edges(path).reduce(adder::add).let { if (closed) adder.add(it, edge(path.last(), path.first())) else it }

fun <V, E> AdditiveGraph<V, E>.travelingSalesmanProblem(): Pair<E, E> =
    hamiltonianCycle.solve(graph.keys.first()).minMaxOfWith(adder) { cost(it, true) }
