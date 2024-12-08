import kotlin.math.absoluteValue

fun main() {
    infix fun Int.distance(other: Int): Int =
        minus(other).absoluteValue

    fun part1(input: List<List<Int>>): Int =
        input.map(List<Int>::sorted)
            .let { (first, second) ->
                first.indices.sumOf { first[it] distance second[it] }
            }

    fun <T> Iterable<T>.frequency(): Map<T, Int> =
        groupingBy { it }.eachCount()

    fun part2(input: List<List<Int>>): Int =
        input.let { (first, second) ->
            val freq = second.frequency()
            first.sumOf { it * freq.getOrDefault(it, 0) }
        }

    fun <T> List<List<T>>.rowSizes(): List<Int> = map(List<*>::size)
    fun <T> List<List<T>>.rowSize(): Int = first().size.also { first -> require(rowSizes().all(first::equals)) }
    fun <T> List<List<T>>.column(index: Int) = List(size) { get(it)[index] }
    fun <T> List<List<T>>.transpose() = List(rowSize(), ::column)


    val input = input().map(String::integers).transpose()
    val one = part1(input).also(::println)
    val two = part2(input).also(::println)
    require(listOf(one, two) == output().map(String::toInt))
}
