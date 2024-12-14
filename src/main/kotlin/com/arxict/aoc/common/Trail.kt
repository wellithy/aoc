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

fun Trail.replaceLast(last: Point): Trail =
    Trail(points.toMutableList().apply { set(lastIndex, last) })
