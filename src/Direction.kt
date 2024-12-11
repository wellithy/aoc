import Direction.*

enum class Direction {
    Up, Right, Down, Left
}

fun Direction.turn(clockwise: Boolean = true): Direction =
    when (this) {
        Up -> if (clockwise) Right else Left
        Right -> if (clockwise) Down else Up
        Down -> if (clockwise) Left else Right
        Left -> if (clockwise) Up else Down
    }
