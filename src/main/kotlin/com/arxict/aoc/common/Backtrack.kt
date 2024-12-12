package com.arxict.aoc.common

interface Backtrack<T> {
    fun accept(candidate: T): Boolean
    fun first(candidate: T): T?
    fun next(candidate: T): T?
}
