import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.absoluteValue
import kotlin.text.get

val TXT_DIR = Path("input")
fun readLines(file: String) = TXT_DIR.resolve(file).readLines()

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

fun <T> Matrix<T>.columnSize(): Int =
    first().size.also { first -> all { it.size == first }.let(::require) }

fun <T> Matrix<T>.transpose(): Matrix<T> =
    List(columnSize(), ::column)

infix fun Int.distance(other: Int): Int =
    minus(other).absoluteValue

fun <T> Iterable<T>.frequency(): Map<T, Int> =
    groupingBy { it }.eachCount()

fun <T> List<T>.remove(index: Int): List<T> =
    toMutableList().apply { removeAt(index) }
