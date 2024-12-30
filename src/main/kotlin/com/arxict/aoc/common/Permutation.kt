package com.arxict.aoc.common

class Permutation {
    companion object {
        fun withReplace(n: Int, k: Int): Sequence<List<Int>> =
            generateSequence(List(n) { 0 }) {
                it.toMutableList().apply {
                    var index = size
                    while (--index >= 0)
                        if (++this[index] == k) this[index] = 0
                        else return@generateSequence this
                    return@generateSequence null
                }
            }
    }
}