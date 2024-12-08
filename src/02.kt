import kotlin.math.absoluteValue

fun main() {

    fun List<Int>.safe(): Int? {
        val increase = first() < last()
        return asSequence().windowed(2){ it[1] - it[0]  }.withIndex().firstOrNull { (_, delta) ->
            delta.absoluteValue !in 1..3 || ((delta > 0) xor increase)
        }?.index
    }

    fun part1(input: List<List<Int>>): Int =
        input.count { it.safe() == null }

    fun <T> List<T>.remove(index: Int): List<T> =
        toMutableList().apply { removeAt(index) }

    fun List<Int>.safeWithDamper(): Int? =
        safe()?.let {
            remove(it.inc()).safe()?.let {
                remove(it).safe()
            }
        }

    fun part2(input: List<List<Int>>): Int =
        input.count { it.safeWithDamper() == null }

    input().let { grid ->
        val input = grid.map(String::integers)
        check(part1(input) to part2(input) == output().map(String::toInt).toPair())
    }
}
