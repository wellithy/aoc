package com.arxict.aoc.common

import com.arxict.aoc.common.Direction.*

enum class Direction {
    Up, Right, Down, Left;
}

val Direction.horizontal: Boolean
    get() = this == Right || this == Left

val Direction.vertical: Boolean
    get() = this == Up || this == Down

operator fun Direction.unaryMinus(): Direction =
    when (this) {
        Up -> Down
        Right -> Left
        Down -> Up
        Left -> Right
    }

fun Direction.turn(clockwise: Boolean = true): Direction =
    if (clockwise) when (this) {
        Up -> Right
        Right -> Down
        Down -> Left
        Left -> Up
    } else when (this) {
        Up -> Left
        Right -> Up
        Down -> Right
        Left -> Down
    }
