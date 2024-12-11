import Direction.*

fun main() {
    data class Point(val row: Int, val column: Int) {
        override fun toString() = "($row,$column)"
    }

    fun solve(grid: List<List<Int>>, start: Point): Pair<Int, Int> {

        fun get(point: Point) = grid.getOrNull(point.row)?.getOrNull(point.column)
        fun Point.move(direction: Direction): Point = when (direction) {
            Right -> copy(column = column.inc())
            Left -> copy(column = column.dec())
            Down -> copy(row = row.inc())
            Up -> copy(row = row.dec())
        }

        fun Point.direction(after: Point): Direction = when {
            after.column == column.inc() -> Right
            after.column == column.dec() -> Left
            after.row == row.inc() -> Down
            after.row == row.dec() -> Up
            else -> error("bug")
        }

        val directions = Direction.entries

        class Trail() : Backtrack<List<Point>> {
            fun solve() = if (get(start) != 0) emptySequence() else solve(listOf(start))
            override fun accept(candidate: List<Point>): Boolean =
                get(candidate.last()) == 9

            fun nextTail(current: Point, start: Int = 0): Point? =
                (start..directions.lastIndex)
                    .map { current.move(directions[it]) }
                    .firstOrNull { get(it) == get(current)!!.inc() }

            override fun first(candidate: List<Point>): List<Point>? =
                nextTail(candidate.last())?.let { candidate + it }

            override fun next(candidate: List<Point>): List<Point>? {
                val prev = candidate[candidate.lastIndex.dec()]
                val dir = directions.indexOf(prev.direction(candidate.last()))
                return nextTail(prev, dir.inc())?.let(candidate::replaceLast)
            }
        }

        val paths = Trail().solve().toSet()
        return paths.map(List<Point>::last).toSet().size to paths.size
    }

    fun solve(lines: List<String>): Pair<Int, Int> {
        val map = lines.map { it.toList().map(Char::digitToInt) }
        val columns = 0..<map.first().size
        return map.indices.asSequence().flatMap { row ->
            columns.asSequence().map { Point(row, it) }
        }.map { solve(map, it) }.reduce { a, b -> a.first + b.first to a.second + b.second }
            .also (::println)
    }
    require(solve(test()) == 36 to 81)
    require(solve(input()).toList() == output().map(String::toInt))
}
