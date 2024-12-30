package com.arxict.aoc.common

class Permutation {
    companion object {
        fun IntArray.nextWithReplace(k: Int): Boolean {
            for (index in lastIndex downTo 0)
                if (this[index] == k - 1) this[index] = 0
                else {
                    this[index]++
                    return true
                }
            return false
        }

        fun withReplace(n: Int, k: Int): Sequence<IntArray> =
            generateSequence(IntArray(n)) {
                it.takeIf { it.nextWithReplace(k) }
            }
    }
}