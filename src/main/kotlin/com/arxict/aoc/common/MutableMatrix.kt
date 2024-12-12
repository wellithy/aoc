package com.arxict.aoc.common

@JvmInline
value class MutableMatrix<T>(val rows: MutableList<MutableList<T>>)
