fun main() {

    val mul= Regex("""mul\((\d+),(\d+)\)""")
    fun part1(input: String): Int =
        mul.findAll(input).sumOf{ it.groupValues[1].toInt() * it.groupValues[2].toInt()}

    val doNoDo= Regex("""do\(\)(.+?)don't\(\)""")
    fun part2(input: String): Int =
        doNoDo.findAll("do()${input}don't()").sumOf{part1(it.groupValues[1]) }


    input().let { grid ->
        val input = grid.joinToString("")
        check(part1(input) to part2(input) == output().map(String::toInt).toPair())
    }
}
