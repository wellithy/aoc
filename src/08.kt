fun main() {
    operator fun Point.div(other: Point) = Point(row - other.row, column - other.column)

    data class Grid(val lines: List<String>) {
        val rowRange = 0..<lines.size
        val columnRange = 0..<lines.first().length
        fun antenna(point: Point): Char? = lines[point.row][point.column].takeUnless { it == '.' }
        val antennas = rowRange.asSequence().flatMap { row -> columnRange.map { Point(row, it) } }
            .filter { antenna(it) != null }.groupBy(::antenna).values

        operator fun contains(point: Point) =
            point.row in rowRange && point.column in columnRange

        operator fun Point.plus(other: Point) =
            Point(row + other.row, column + other.column).takeIf(::contains)

        operator fun Point.minus(other: Point) =
            Point(row - other.row, column - other.column).takeIf(::contains)

        fun MutableSet<Point>.add(node: Pair<Point, Point>, resonant: Boolean) {
            val d = node.first / node.second
            if (resonant) {
                generateSequence(node.first) { it + d }.let(::addAll)
                generateSequence(node.second) { it - d }.let(::addAll)
            } else {
                (node.first + d)?.also(::add)
                (node.second - d)?.also(::add)
            }
        }

        fun MutableSet<Point>.add(locations: List<Point>, resonant: Boolean) =
            locations.asSequence().flatMapIndexed { i, p ->
                (i + 1..locations.lastIndex).asSequence().map { p to locations[it] }
            }.forEach { add(it, resonant) }

        fun solve(resonant: Boolean) = buildSet {
            antennas.forEach { add(it, resonant) }
        }.size

    }

    fun solve(strings: List<String>): Pair<Int, Int> =
        with(Grid(strings)) {
            (solve(false) to solve(true)).also(::println)
        }

    require(solve(test()) == 14 to 34)
    require(solve(input()).toList() == output().map(String::toInt))
}
