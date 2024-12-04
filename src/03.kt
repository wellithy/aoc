fun main() {

    val mul= Regex("""mul\((\d+),(\d+)\)""")
    fun part1(input: String): Int =
        mul.findAll(input).sumOf{ it.groupValues[1].toInt() * it.groupValues[2].toInt()}

    val doNoDo= Regex("""do\(\)(.+?)don't\(\)""")
    fun part2(input: String): Int =
        doNoDo.findAll("do()${input}don't()").sumOf{part1(it.groupValues[1]) }


    inOut("03").let { (grid, one, two) ->
        val all = grid.joinToString("")
        check(part1(all) == one)
        check(part2(all) == two)
    }
}
