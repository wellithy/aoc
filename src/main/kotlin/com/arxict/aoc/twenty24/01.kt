import kotlin.math.absoluteValue

fun main() {
    infix fun Int.distance(other: Int): Int =
        minus(other).absoluteValue

    fun part1(input: Matrix<Int>): Int =
        input.rows.map(List<Int>::sorted)
            .let { (first, second) ->
                first.indices.sumOf { first[it] distance second[it] }
            }

    fun <T> Iterable<T>.frequency(): Map<T, Int> =
        groupingBy { it }.eachCount()

    fun part2(input: Matrix<Int>): Int =
        input.rows.let { (first, second) ->
            val freq = second.frequency()
            first.sumOf { it * freq.getOrDefault(it, 0) }
        }

    val input = input().map(String::integers).let(::Matrix).transpose()
    val one = part1(input).also(::println)
    val two = part2(input).also(::println)
    require(listOf(one, two) == output().map(String::toInt))
}
