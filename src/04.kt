fun main() {

    fun Grid.search(words: Set<String>, possible: (Point) -> List<Path>): Int {
        val firsts = words.map(String::first).toSet()
        return mapIndexed { row, line ->
            line.mapIndexed { column, c ->
                if (c !in firsts) 0 else possible(row to column)
                    .mapNotNull(::get)
                    .count(words::contains)
            }.sum()
        }.sum()
    }

    fun part1(input: Grid): Int {
        val word = "XMAS"
        val length = 0..<word.length
        val factors = listOf(0 to 1, 1 to 1, 1 to 0, 1 to -1, 0 to -1, -1 to -1, -1 to 0, -1 to 1)
        fun Point.path(factor: Point) = length.map { this + it * factor }
        return input.search(setOf(word)) { factors.map(it::path) }
    }

    fun part2(input: Grid): Int {
        val x = listOf(0 to 0, 0 to 2, 1 to 1, 2 to 0, 2 to 2)
        fun Point.x() = x.map(::plus)
        return input.search(setOf("MSAMS", "SSAMM", "MMASS", "SMASM")) { listOf(it.x()) }
    }

    inOut("04").let { (grid, one, two) ->
        check(part1(grid) == one)
        check(part2(grid) == two)
    }
}
