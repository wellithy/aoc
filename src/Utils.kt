import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.absoluteValue
import kotlin.text.split

typealias Grid = List<String>

val INPUT = Path("in")
fun input(file: String): Grid = INPUT.resolve(file).readLines()
val OUTPUT = Path("out")
fun output(file: String): List<Int> = OUTPUT.resolve(file).readLines().map(String::toInt)
fun inOut(file: String): Triple<Grid, Int, Int> = output(file).let { Triple(input(file), it[0], it[1]) }

@OptIn(ExperimentalStdlibApi::class)
fun digest(str: String, name: String = "MD5") = with(MessageDigest.getInstance(name)) {
    str.toByteArray().let(::digest).toHexString().padStart(digestLength * 2, '0')
}

fun Any?.println() = println(this)

fun String.numbers(regex: String = ","): List<Int> =
    split(regex).map(String::toInt)

fun String.intPair(regex: String): Pair<Int, Int> =
    numbers(regex).let { it[0] to it[1] }

typealias Matrix<T> = List<List<T>>

private val NUMBERS = Regex("""\D+""")
fun List<String>.numbers(): Matrix<Int> =
    map { it.split(NUMBERS).map(String::toInt) }

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
