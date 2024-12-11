open class Matrix<T>(val rows: List<List<T>>)

fun Matrix(lines: List<String>): Matrix<Char> =
    Matrix(lines.map(String::toList))

private fun Matrix<*>.rowSize() =
    rows.first().size
        .also { first -> require(rows.map(List<*>::size).all(first::equals)) }

val Matrix<*>.rowRange: IntRange get() = 0..<rows.size
val Matrix<*>.columnRange: IntRange get() = 0..<rowSize()

operator fun Matrix<*>.contains(point: Point): Boolean =
    with(point) { row in rowRange && column in columnRange }

operator fun <T> Matrix<T>.get(point: Point): T? =
    with(point) { rows.getOrNull(row)?.getOrNull(column) }

fun <T> Matrix<T>.column(index: Int): List<T> =
    List(rows.size) { rows[it][index] }

fun <T> Matrix<T>.transpose(): Matrix<T> =
    Matrix(List(rowSize(), ::column))

fun Matrix<*>.points(): Sequence<Point> =
    rows.indices.asSequence().flatMap { row ->
        columnRange.asSequence().map { Point(row, it) }
    }
