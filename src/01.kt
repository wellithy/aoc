fun main() {
    fun part1(input: Matrix<Int>): Int =
        input.map(List<Int>::sorted)
            .let { (first, second) ->
                first.indices.sumOf { first[it] distance second[it] }
            }

    fun part2(input: Matrix<Int>): Int =
        input.let { (first, second) ->
                val freq = second.frequency()
                first.sumOf { it * freq.getOrDefault(it, 0) }
            }

    inOut("01").let { (grid, one, two) ->
        val input = grid.numbers().transpose()
        check(part1(input) == one)
        check(part2(input) == two)
    }
}
