import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.absoluteValue
import kotlin.streams.asSequence
import kotlin.text.split

typealias Grid = List<String>

val INPUT = Path("in")
fun input(file: String): Grid = INPUT.resolve(file).readLines()
val OUTPUT = Path("out")
fun output(file: String): List<Int> = OUTPUT.resolve(file).readLines().map(String::toInt)
fun inOut(file: String = callerDay()): Triple<Grid, Int, Int> = output(file).let { Triple(input(file), it[0], it[1]) }

private val DAY = Regex("""(\d\d)""")
private fun callerDay(): String =
    StackWalker.getInstance().walk { s ->
        s.asSequence().firstNotNullOf { DAY.find(it.className)?.groupValues[1] }
    }

private val NUMBERS = Regex("""\D+""")
fun String.numbers(regex: Regex = NUMBERS): List<Int> =
    split(regex).map(String::toInt)

fun String.numbers(delimiter: Char): List<Int> =
    split(delimiter).map(String::toInt)

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
