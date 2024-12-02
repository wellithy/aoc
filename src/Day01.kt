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

    val input = readLines("Day01").numbers().transpose()
    val results = readLines("Day01-results").map(String::toInt)

    check(part1(input) == results[0])
    check(part2(input) == results[1])
}
