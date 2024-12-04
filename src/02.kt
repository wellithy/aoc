import kotlin.math.absoluteValue

fun main() {

    fun List<Int>.safe(): Int? {
        val increase = first() < last()
        return asSequence().windowed(2){ it[1] - it[0]  }.withIndex().firstOrNull { (_, delta) ->
            delta.absoluteValue !in 1..3 || ((delta > 0) xor increase)
        }?.index
    }

    fun part1(input: Matrix<Int>): Int =
        input.count { it.safe() == null }

    fun List<Int>.safeWithDamper(): Int? =
        safe()?.let {
            remove(it.inc()).safe()?.let {
                remove(it).safe()
            }
        }

    fun part2(input: Matrix<Int>): Int =
        input.count { it.safeWithDamper() == null }

    val input = input("02").numbers()
    val results = output("02")

    check(part1(input) == results[0])
    check(part2(input) == results[1])
}
