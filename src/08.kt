fun main() {
    data class Point(val row: Int, val column: Int)

    data class Grid(val lines: List<String>) {
        val rowRange = 0..<lines.size
        val columnRange = 0..<lines.first().length
    }

    operator fun Grid.get(point: Point): Char = lines[point.row][point.column]

    fun Grid.frequency(): Map<Char, List<Point>> =
        rowRange.flatMap { row -> columnRange.map { Point(row, it) } }
            .groupByTo(mutableMapOf()) { get(it) }.apply { remove('.') }

    fun Grid.point(row: Int, column: Int): Point? =
        takeIf { row in rowRange && column in columnRange }?.let { Point(row, column) }

    fun Point.diff(other: Point) = Point(row - other.row, column - other.column)
    fun Grid.add(a: Point, b: Point) = point(a.row + b.row, a.column + b.column)
    fun Grid.minus(a: Point, b: Point) = point(a.row - b.row, a.column - b.column)

    fun Grid.add(set: MutableSet<Point>, a: Point, b: Point) = with(set) {
        val d = a.diff(b)
        add(a, d)?.also { add(it) }
        minus(b, d)?.also { add(it) }
    }

    fun Grid.addResonant(set: MutableSet<Point>, a: Point, b: Point) = with(set) {
        val d = a.diff(b)
        generateSequence(a){add(it, d)}.let(::addAll)
        generateSequence(b){minus(it, d)}.let(::addAll)
    }

    fun Grid.add(set: MutableSet<Point>, a: Point, points: List<Point>, resonant: Boolean) =
        if (resonant) points.forEach { addResonant(set, a, it) }
        else points.forEach { add(set, a, it) }

    fun Grid.add(set: MutableSet<Point>, points: List<Point>, resonant: Boolean) =
        points.forEachIndexed { i, a -> add(set, a, points.subList(i + 1, points.size), resonant) }

    fun solve(strings: List<String>): Pair<Int, Int> {
        val grid = Grid(strings)
        val freq = grid.frequency().values
        val part1 = buildSet {
            freq.forEach { grid.add(this, it, false) }
        }.size
        val part2 = buildSet {
            freq.forEach { grid.add(this, it, true) }
        }.size

        return (part1 to part2).also(::println)
    }

    require(solve(test()) == 14 to 34)
    require(solve(input()).toList() == output().map(String::toInt))
}
