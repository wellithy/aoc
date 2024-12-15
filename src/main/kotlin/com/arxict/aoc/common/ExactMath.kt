package com.arxict.aoc.common

@Throws(ArithmeticException::class)
infix fun Long.multiplyExact(other: Long): Long =
    Math.multiplyExact(this, other)

@Throws(ArithmeticException::class)
infix fun Long.subtractExact(other: Long): Long =
    Math.subtractExact(this, other)

@Throws(ArithmeticException::class)
infix fun Long.divideExact(other: Long): Long =
    Math.divideExact(this, other)
