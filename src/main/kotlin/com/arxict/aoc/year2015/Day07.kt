package com.arxict.aoc.year2015


class Day07(lines: List<String>) {
    companion object {
        enum class UnaryOperator(val operation: (Int) -> Int) {
            NOT(Int::inv),
        }

        enum class BinaryOperator(val operation: (Int, Int) -> Int) {
            LSHIFT(Int::shl),
            RSHIFT(Int::shr),
            AND(Int::and),
            OR(Int::or),
            ;
        }

        sealed class Term
        data class Num(val num: Int) : Term()
        data class Id(val id: String) : Term()

        fun Term(term: String): Term =
            term.toIntOrNull()?.let { Num(it) } ?: Id(term)

        fun Term.value(): Int? = if (this is Num) num else null

        sealed class Expression
        data class Single(val term: Term) : Expression()
        data class Unary(val operator: UnaryOperator, val term: Term) : Expression()
        data class Binary(val operator: BinaryOperator, val left: Term, val right: Term) : Expression()

        fun Expression.value(): Int? = if (this is Single) term.value() else null

        fun Expression(expression: String): Expression =
            with(expression.split(' ')) {
                when (size) {
                    1 -> Single(Term(first()))
                    2 -> Unary(UnaryOperator.valueOf(first()), Term(last()))
                    3 -> Binary(BinaryOperator.valueOf(this[1]), Term(first()), Term(last()))
                    else -> error(expression)
                }
            }

    }

    val expressions = buildMap {
        val lineRegex = Regex("""(.+) -> (.+)""")
        lines.forEach {
            lineRegex.find(it)!!.groupValues.let { (_, expression, id) ->
                set(id, Expression(expression))
            }
        }
    }

    fun fill(init: Map<String, Int> = emptyMap()): Map<String, Expression> {
        val evaluated = expressions.toMutableMap().apply { init.forEach { set(it.key, Single(Num(it.value))) } }
        fun Term.eval(): Int? =
            when (this) {
                is Num -> num
                is Id -> evaluated.getValue(id).value()
            }

        fun Expression.eval(): Int? =
            when (this) {
                is Single -> term.eval()
                is Unary -> term.eval()?.let { operator.operation(it) }
                is Binary -> left.eval()?.let { one -> right.eval()?.let { two -> operator.operation(one, two) } }
            }

        do {
            var modified = false
            evaluated.forEach { (id, expression) ->
                if (expression.value() == null)
                    expression.eval()?.let {
                        modified = true
                        evaluated[id] = Single(Num(it))
                    }
            }
        } while (modified)
        return evaluated
    }

    fun solve(): Pair<Int, Int> {
        val part1 = fill().getValue("a").value()!!
        return part1 to fill(mapOf("b" to part1)).getValue("a").value()!!
    }
}
