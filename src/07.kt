fun main() {
    class Line(val result: Long, val operands: List<Int>)

    fun Line(str: String) = str.split(':').let {
        Line(it[0].toLong(), it[1].integers())
    }

    fun combination(n: Int, m: Int) = object : Iterator<List<Int>> {
        var more = true
        val state = MutableList(n) { 0 }
        override fun hasNext() = more
        override fun next() =
            if (!more) throw NoSuchElementException()
            else state.toList().also {
                var index = 0
                while (more) {
                    if (++state[index] < m) break
                    state[index++] = 0
                    more = index < n
                }
            }
    }

    fun Line.evaluate(operators: List<Int>) = with(operands.iterator()) {
        operators.fold(next().toLong()) { result, operand ->
            val num = next()
            when (operand) {
                0 -> result + num
                1 -> result * num
                2 -> "$result$num".toLong()
                else -> error("BUG")
            }
        }
    }

    fun Line.valid(operators: Int) =
        combination(operands.size - 1, operators).asSequence().firstOrNull { evaluate(it) == result } != null

    fun solve(strings: List<String>): Pair<Long, Long> {
        val matrix = strings.map(::Line)
        return matrix.filter { it.valid(2) }.sumOf(Line::result) to
                matrix.filter { it.valid(3) }.sumOf(Line::result)
    }

    check(solve(test()) == 3749L to 11387L)

    check(solve(input()) == output().map(String::toLong).toPair())
}
