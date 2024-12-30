package com.arxict.aoc.common

interface Backtrack<T> {
    fun accept(candidate: T): Boolean
    fun first(candidate: T): T?
    fun next(candidate: T): T?
}

fun <T> Backtrack<T>.solve(
    root: T,
    nextAfterAccept: Boolean = false,
): Sequence<T> = sequence {
    with(ArrayDeque<T>()) {
        add(root)
        while (isNotEmpty()) {
            val candidate = removeFirst()
            if (accept(candidate)) {
                yield(candidate)
                if (!nextAfterAccept) continue
            }
            first(candidate)?.let {
                var test = it
                while (true) {
                    addFirst(test)
                    test = next(test) ?: break
                }
            }
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
