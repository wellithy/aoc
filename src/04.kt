fun main() {

    fun List<String>.letter(point: Point) = getOrNull(point.first)?.getOrNull(point.second)
    fun List<String>.word(points: List<Point>) = points.map(::letter).joinToString("")
    fun List<String>.search(words: Set<String>, possible: (Point) -> List<List<Point>>): Int {
        val firsts = words.map(String::first).toSet()
        return mapIndexed { row, line ->
            line.mapIndexed { column, c ->
                if (c !in firsts) 0 else possible(row to column)
                    .map(::word)
                    .count(words::contains)
            }.sum()
        }.sum()
    }

    fun part1(input: List<String>): Int {
        val word = "XMAS"
        val length = word.length
        val factors = listOf(0 to 1, 1 to 0, 1 to 1, 1 to -1)

        return input.search(setOf(word, word.reversed())) { (row, column) ->
            factors.map { (rf, cf) ->
                (0..<length).map { row + rf * it to column + cf * it }
            }
        }
    }


    fun part2(input: List<String>): Int {
        val x = listOf(0 to 0, 0 to 2, 1 to 1, 2 to 0, 2 to 2)

        return input.search(setOf("MSAMS", "SSAMM", "MMASS", "SMASM")) { (row, column) ->
            listOf(x.map { row + it.first to column + it.second })
        }
    }

    val input = input("04")
    val results = output("04")
    check(part1(input) == results[0])
    check(part2(input) == results[1])
}
