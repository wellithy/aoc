import Direction.*

fun main() {
    fun solve(matrix: Matrix<Int>, start: Point): Pair<Int, Int> {
        fun Point.direction(after: Point): Direction = when {
            after.column == column.inc() -> Right
            after.column == column.dec() -> Left
            after.row == row.inc() -> Down
            after.row == row.dec() -> Up
            else -> error("bug")
        }

        val directions = Direction.entries

        class Trail() : Backtrack<List<Point>> {
            fun solve() = if (matrix[start] != 0) emptySequence() else solve(listOf(start))
            override fun accept(candidate: List<Point>): Boolean =
                matrix[(candidate.last())] == 9

            fun nextTail(current: Point, start: Int = 0): Point? =
                (start..directions.lastIndex)
                    .map { current.move(directions[it]) }
                    .firstOrNull { matrix[it] == matrix[current]!!.inc() }

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
        val map = Matrix(lines.map { it.toList().map(Char::digitToInt) })
        return map.points().map { solve(map, it) }
            .reduce { a, b -> a.first + b.first to a.second + b.second }
            .also(::println)
    }
    require(solve(test()) == 36 to 81)
    require(solve(input()).toList() == output().map(String::toInt))
}
