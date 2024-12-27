package com.arxict.aoc.common

private typealias TL = Triple<Long, Long, Long>

class LinearEquationSolver {
    companion object {

        @Throws(ArithmeticException::class)
        fun whole(eq1: TL, eq2: TL): Pair<Long, Long>? =
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
        fun solve(eq1: TL, eq2: TL): TL {
            fun determinant(a: TL.() -> Long, b: TL.() -> Long): Long =
                (eq1.a() multiplyExact eq2.b()) subtractExact (eq1.b() multiplyExact eq2.a())
            return TL(
                determinant(TL::third, TL::second),
                determinant(TL::first, TL::third),
                determinant(TL::first, TL::second),
            )
        }
    }
}