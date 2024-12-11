fun main() {

    val mul= Regex("""mul\((\d+),(\d+)\)""")
    fun part1(input: String): Int =
        mul.findAll(input).sumOf{ it.groupValues[1].toInt() * it.groupValues[2].toInt()}

    val doNoDo= Regex("""do\(\)(.+?)don't\(\)""")
    fun part2(input: String): Int =
        doNoDo.findAll("do()${input}don't()").sumOf{part1(it.groupValues[1]) }

    val input = input().joinToString("")
    val one = part1(input).also(::println)
    val two = part2(input).also(::println)
    require(listOf(one, two) == output().map(String::toInt))
}
