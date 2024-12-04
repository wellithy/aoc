import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.absoluteValue

typealias Point = Pair<Int, Int>
typealias Displacement = Pair<Int, Int>
typealias Path = List<Point>
typealias Grid = List<String>

operator fun Point.plus(displacement: Displacement): Point = first + displacement.first to second + displacement.second
operator fun Int.times(point: Point): Point = times(point.first) to times(point.second)
operator fun Grid.get(point: Point): Char? = getOrNull(point.first)?.getOrNull(point.second)
operator fun Grid.get(path: Path): String? = path.map { get(it) ?: return@get null }.joinToString("")

val INPUT = Path("input")
fun input(file: String): Grid = INPUT.resolve(file).readLines()
val OUTPUT = Path("output")
fun output(file: String): List<Int> = OUTPUT.resolve(file).readLines().map(String::toInt)
fun inOut(file: String): Triple<Grid, Int, Int> = output(file).let { Triple(input(file), it[0], it[1]) }

@OptIn(ExperimentalStdlibApi::class)
fun digest(str: String, name: String = "MD5") = with(MessageDigest.getInstance(name)) {
    str.toByteArray().let(::digest).toHexString().padStart(digestLength * 2, '0')
}

fun Any?.println() = println(this)

val NUMBERS = Regex("""\D+""")
fun String.numbers(): List<Int> =
    split(NUMBERS).map(String::toInt)

typealias Matrix<T> = List<List<T>>

fun List<String>.numbers(): Matrix<Int> =
    map(String::numbers)

fun <T> Matrix<T>.column(index: Int): List<T> =
    List(size) { get(it)[index] }

fun <T> Matrix<T>.rowSize(): Int =
    first().size.also { first -> all { it.size == first }.let(::require) }

fun <T> Matrix<T>.transpose(): Matrix<T> =
    List(rowSize(), ::column)

infix fun Int.distance(other: Int): Int =
    minus(other).absoluteValue

fun <T> Iterable<T>.frequency(): Map<T, Int> =
    groupingBy { it }.eachCount()

fun <T> List<T>.remove(index: Int): List<T> =
    toMutableList().apply { removeAt(index) }
