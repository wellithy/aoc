package com.arxict.aoc.common

interface Backtrack<T> {
    fun accept(candidate: T): Boolean
    fun first(candidate: T): T?
    fun next(candidate: T): T?
}

fun <T> Backtrack<T>.solve(root: T): Sequence<T> = sequence {
    val dq = ArrayDeque<T>().apply { add(root) }
    while (dq.isNotEmpty()) {
        val candidate = dq.removeFirst()
        if (accept(candidate)) yield(candidate)
        first(candidate)?.let {
            var test = it.also(dq::addFirst) // <<< DFS vvv
            while (true) test = next(test)?.also(dq::addLast) ?: break
        }
    }
}

fun <T> Backtrack<T>.solveRecursively(root: T): Sequence<T> = sequence {
    solve(this@solveRecursively, root)
}

private suspend fun <T> SequenceScope<T>.solve(backtrack: Backtrack<T>, candidate: T) {
    if (backtrack.accept(candidate)) yield(candidate)
    backtrack.first(candidate)?.let {
        var test = it
        while (true) {
            solve(backtrack, test)
            test = backtrack.next(test) ?: break
        }
    }
}
