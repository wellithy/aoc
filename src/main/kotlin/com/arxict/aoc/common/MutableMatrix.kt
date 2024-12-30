package com.arxict.aoc.common

interface MutableMatrix<T> : Matrix<T> {
    override val matrix: MutableList<MutableList<T>>
}

@JvmInline
private value class MutableMatrixImpl<T>(override val matrix: MutableList<MutableList<T>>) : MutableMatrix<T>

fun <T> MutableMatrix(matrix: MutableList<MutableList<T>>): MutableMatrix<T> =
    MutableMatrixImpl(matrix)

operator fun <T> MutableMatrix<T>.set(point: Point, value: T): T =
    with(point) { matrix[row].set(column, value) }

fun <T> MutableMatrix<T>.swap(a: Point, b: Point) {
    set(b, set(a, get(b)))
}
