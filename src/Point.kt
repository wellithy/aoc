import Direction.*

data class Point(val row: Int, val column: Int)

fun Point.move(direction: Direction, count: Int = 1): Point =
    when (direction) {
        Up -> copy(row = row - count)
        Down -> copy(row = row + count)
        Right -> copy(column = column + count)
        Left -> copy(column = column - count)
    }

operator fun Point.plus(other: Point): Point =
    Point(row + other.row, column + other.column)
