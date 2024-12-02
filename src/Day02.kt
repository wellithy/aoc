import kotlin.math.absoluteValue

fun main() {

    fun List<Int>.safe(): Int {
        val increase = first() < last()
        var prev = first()
        subList(1, size).forEachIndexed { i, x ->
            val delta = x - prev
            if (delta.absoluteValue !in 1..3 || ((delta > 0) xor increase))
                return i + 1
            prev = x
        }
        return -1
    }

    fun part1(input: List<String>): Int =
        input.numbers().count { it.safe() < 0 }

    fun List<Int>.safeWithDamper(): Boolean {
        val index = safe()
        return if (index < 0) true
        else remove(index.inc()).safe() < 0 || remove(index).safe() < 0 || remove(index.dec()).safe() < 0
    }

    fun part2(input: List<String>): Int =
        input.numbers().count(List<Int>::safeWithDamper)

    val input = readLines("Day02")
    val results = readLines("Day02-results").map(String::toInt)

    check(part1(input) == results[0])
    check(part2(input) == results[1])
}
