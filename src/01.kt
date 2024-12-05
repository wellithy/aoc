import kotlin.math.absoluteValue

fun main() {
    infix fun Int.distance(other: Int): Int =
        minus(other).absoluteValue

    fun part1(input: Matrix<Int>): Int =
        input.map(List<Int>::sorted)
            .let { (first, second) ->
                first.indices.sumOf { first[it] distance second[it] }
            }

    fun <T> Iterable<T>.frequency(): Map<T, Int> =
        groupingBy { it }.eachCount()

    fun part2(input: Matrix<Int>): Int =
        input.let { (first, second) ->
            val freq = second.frequency()
            first.sumOf { it * freq.getOrDefault(it, 0) }
        }

    fun <T> Matrix<T>.column(index: Int): List<T> =
        List(size) { get(it)[index] }

    fun Matrix<*>.rowSizes(): List<Int> =
        map(List<*>::size)

    fun <T> Matrix<T>.rowSize(): Int =
        first().size.also { first -> require(rowSizes().all(first::equals)) }

    fun <T> Matrix<T>.transpose(): Matrix<T> =
        List(rowSize(), ::column)

    inOut().let { (grid, one, two) ->
        val input = grid.numbers().transpose()
        check(part1(input) == one)
        check(part2(input) == two)
    }
}
