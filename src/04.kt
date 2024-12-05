private typealias Point = Pair<Int, Int>
private typealias Trail = List<Point>

private fun Trail(direction: Point, length: Int): Trail =
    (0..<length).map { it * direction.first to it * direction.second }

private operator fun Point.plus(other: Point): Point =
    first + other.first to second + other.second

private operator fun Grid.get(point: Point): Char? = getOrNull(point.first)?.getOrNull(point.second)
private operator fun Grid.get(point: Point, trail: Trail): String? =
    trail.map { get(point + it) ?: return@get null }.joinToString("")

fun main() {

    fun Grid.search(words: Set<String>, possible: List<Trail>): Int {
        val firsts = words.map(String::first).toSet()
        return mapIndexed { row, line ->
            line.mapIndexed { column, c ->
                val point = Point(row, column)
                if (c !in firsts) 0
                else possible
                    .mapNotNull { get(point, it) }
                    .count(words::contains)
            }.sum()
        }.sum()
    }

    fun part1(input: Grid): Int {
        val word = "XMAS"
        val directions = listOf(0 to 1, 1 to 1, 1 to 0, 1 to -1)
        val trails = directions.map { Trail(it, word.length) }
        return input.search(setOf(word, word.reversed()), trails)
    }

    fun part2(input: Grid): Int {
        val word = "MAS"
        val words = listOf(word, word.reversed())
        val all = words.flatMap { w -> words.map { w + it } }.toSet()
        val trail = listOf(0 to 0, 1 to 1, 2 to 2, 0 to 2, 1 to 1, 2 to 0)
        return input.search(all, listOf(trail))
    }

    inOut().let { (grid, one, two) ->
        check(part1(grid) == one)
        check(part2(grid) == two)
    }
}
