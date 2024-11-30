fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    check(part1(listOf("first line")) == 1)

    val testInput = "Day01_test".lines
    check(part1(testInput) == 0)

    val input = "Day01".lines
    part1(input).println()
    part2(input).println()

}
