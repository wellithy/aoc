import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int =
         input
            .asSequence()
            .map { it.split("   ") }
            .map { it[0].toInt() to it[1].toInt() }
            .unzip()
            .let{it.first.sorted() zip it.second.sorted()}
            .sumOf{(it.first - it.second).absoluteValue}

    fun part2(input: List<String>): Int {
        val (left, right) = input
            .asSequence()
            .map { it.split("   ") }
            .map { it[0].toInt() to it[1].toInt() }
            .unzip()
        return left.sumOf { l -> right.count{it==l}*l  }
    }

    val input = "Day01".let(::lines)
    part1(input).println()
    part2(input).println()

}
