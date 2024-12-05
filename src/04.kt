private typealias Point = Pair<Int, Int>
private typealias Displacement = Pair<Int, Int>
private typealias Direction = Pair<Int, Int>
private typealias Trail = List<Point>

private operator fun Point.plus(displacement: Displacement): Point =
    first + displacement.first to second + displacement.second

private operator fun Trail.plus(point: Point): Trail = map { it + point }
private operator fun Int.times(point: Point): Point = times(point.first) to times(point.second)
private operator fun Point.times(scale: Int): Point = scale * this
private operator fun Grid.get(point: Point): Char? = getOrNull(point.first)?.getOrNull(point.second)
private operator fun Grid.get(trail: Trail): String? = trail.map { get(it) ?: return@get null }.joinToString("")

fun main() {

    fun Grid.search(words: Set<String>, possible: List<Trail>): Int {
        val firsts = words.map(String::first).toSet()
        return mapIndexed { row, line ->
            line.mapIndexed { column, c ->
                val point = Point(row, column)
                if (c !in firsts) 0
                else possible
                    .mapNotNull { get(it + point) }
                    .count(words::contains)
            }.sum()
        }.sum()
    }

    fun part1(input: Grid): Int {
        val word = "XMAS"
        val length = 0..<word.length
        val directions = listOf(0 to 1, 1 to 1, 1 to 0, 1 to -1)
        fun trail(direction: Point) = length.map(direction::times)
        val trails = directions.map(::trail)
        return input.search(setOf(word, word.reversed()), trails)
    }
    /*
    ....XXMAS.
    .SAMXMS...
    ...S..A...
    ..A.A.MS.X
    XMASAMX.MM
    X.....XA.A
    S.S.S.S.SS
    .A.A.A.A.A
    ..M.M.M.MM
    .X.X.XMASX

        MAS SAM
        SAM SAM
        MAS MAS
        SAM MAS

        "), listOf(trail))
     */

    fun part2(input: Grid): Int {
        val word = "MAS"
        val words = listOf(word, word.reversed())
        val all = words.flatMap { w -> words.map { w + it } }.toSet()
        val trail = listOf(0 to 0, 1 to 1, 2 to 2, 0 to 2, 1 to 1, 2 to 0)
        return input.search(all, listOf(trail))
    }

    inOut("04").let { (grid, one, two) ->
        check(part1(grid) == one)
        check(part2(grid) == two)
    }
}
