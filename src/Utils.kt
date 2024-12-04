import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.absoluteValue

val INPUT = Path("input")
fun input(file: String) = INPUT.resolve(file).readLines()
val OUTPUT = Path("output")
fun output(file: String) = OUTPUT.resolve(file).readLines().map(String::toInt)

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
