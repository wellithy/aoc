import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.absoluteValue

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

fun List<String>.numbers(): List<List<Int>> =
    map(String::numbers)

fun <T> List<List<T>>.transpose(): List<List<T>> =
    first().indices.map { column -> indices.map { row -> get(row)[column] } }

infix fun Int.distance(other: Int) : Int =
    minus(other).absoluteValue

fun <T> Iterable<T>.frequency(): Map<T, Int> =
    groupingBy { it }.eachCount()
