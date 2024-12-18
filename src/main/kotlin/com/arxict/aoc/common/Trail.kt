package com.arxict.aoc.common

@JvmInline
value class Trail(val points: List<Point>)

fun Trail.last(): Point =
    points.last()

val Trail.lastIndex: Int
    get() = points.lastIndex

operator fun Trail.plus(point: Point): Trail =
    Trail(points + point)

operator fun Trail.get(index: Int): Point =
    points[index]

operator fun Trail.contains(point: Point): Boolean =
    point in points

fun Trail.replaceLast(last: Point): Trail =
    Trail(points.toMutableList().apply { set(lastIndex, last) })

fun Trail.directions(): Sequence<Direction?> =
    points.asSequence().windowed(2).map { (a, b) -> (b - a).toDirection() }
