import Direction.*

enum class Direction { Up, Right, Down, Left }

fun main() {
    val start = '^'
    val block = '#'

    data class Point(val row: Int, val column: Int)
    data class Location(val point: Point, val direction: Direction)

    fun Location.next(): Location {
        var r = point.row
        var c = point.column
        return when (direction) {
            Up -> r--
            Down -> r++
            Left -> c--
            Right -> c++
        }.let { Location(Point(r, c), direction) }
    }

    fun Location.turn() = copy(
        direction = when (direction) {
            Up -> Right
            Right -> Down
            Down -> Left
            Left -> Up
        }
    )

    operator fun <Char> List<List<Char>>.get(point: Point): Char? = with(point) { getOrNull(row)?.getOrNull(column) }
    operator fun List<MutableList<Char>>.set(point: Point, value: Char) =
        with(point) { getOrNull(row)!!.set(column, value) }

    fun List<List<*>>.start() =
        withIndex().firstNotNullOf { (row, line) ->
            line.withIndex().firstOrNull { it.value == start }?.let { Location(Point(row, it.index), Direction.Up) }
        }

    fun path(lists: List<List<Char>>, start: Location): Set<Location>? = buildSet {
        var current = start
        while (true) {
            if (!add(current)) return null
            val next = current.next()
            current = if ((lists[next.point] ?: break) == block) current.turn()
            else next
        }
    }

    fun solve(strings: List<String>): Pair<Int, Int> {
        val matrix = strings.map(String::toMutableList)
        val start = matrix.start()
        var part1 = 0
        var part2 = 0
        path(matrix, start)!!.distinctBy(Location::point).forEach { location ->
            part1++
            val orig = matrix[location.point]!!
            matrix[location.point] = block
            if (path(matrix, start) == null) part2++
            matrix[location.point] = orig
        }
        return part1 to part2
    }

    check(solve(test()) == 41 to 6)
    val solution = solve(input()).also(::println)
    require(solution.toList() == output().map(String::toInt))
}
