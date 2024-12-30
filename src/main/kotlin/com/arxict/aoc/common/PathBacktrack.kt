package com.arxict.aoc.common

abstract class PathBacktrack<V> : Backtrack<List<V>> {
    abstract fun neighbors(vertex: V): Sequence<V>

    fun solve(start: V): Sequence<List<V>> =
        solve(listOf(start))

    fun solve(starts: Sequence<V>): Sequence<List<V>> =
        starts.flatMap(::solve)

    override fun first(candidate: List<V>): List<V>? =
        neighbors(candidate.last()).next(candidate)

    override fun next(candidate: List<V>): List<V>? =
        candidate.getOrNull(candidate.lastIndex.dec())?.let { prev ->
            neighbors(prev).dropWhile { it != candidate.last() }.drop(1)
                .next(candidate.subList(0, candidate.lastIndex))
        }

    fun Sequence<V>.next(candidate: List<V>): List<V>? =
        firstOrNull { it !in candidate }?.let { candidate + it }
}
