package com.arxict.aoc.common

import kotlin.Throws

typealias TripleLong = Triple<Long, Long, Long>

class LinearEquationSolver {
    companion object {

        @Throws(ArithmeticException::class)
        fun whole(eq1: TripleLong, eq2: TripleLong): Pair<Long, Long>? =
            with(solve(eq1, eq2)) {
                takeUnless { third == 0L || first % third != 0L || second % third != 0L }
                    ?.let { (first divideExact third) to (second divideExact third) }
            }

        /*
        https://en.wikipedia.org/wiki/Cramer%27s_rule#Explicit_formulas_for_small_systems

        eq1.first * x + eq1.second * y = eq1.third
        eq2.first * x + eq2.second * y = eq2.third

        with(solve2x2(eq1, eq2)){
            x = first / third
            y = second / third
        }
         */
        @Throws(ArithmeticException::class)
        fun solve(eq1: TripleLong, eq2: TripleLong): TripleLong {
            fun determinant(a: TripleLong.() -> Long, b: TripleLong.() -> Long): Long =
                (eq1.a() multiplyExact eq2.b()) subtractExact (eq1.b() multiplyExact eq2.a())
            return TripleLong(
                determinant(TripleLong::third, TripleLong::second),
                determinant(TripleLong::first, TripleLong::third),
                determinant(TripleLong::first, TripleLong::second),
            )
        }
    }
}