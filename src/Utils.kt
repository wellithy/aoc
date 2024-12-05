import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.streams.asSequence
import kotlin.text.split

typealias Grid = List<String>
typealias Matrix<T> = List<List<T>>

val INPUT = Path("in")
fun input(file: String): Grid = INPUT.resolve(file).readLines()
val OUTPUT = Path("out")
fun output(file: String): List<Int> = OUTPUT.resolve(file).readLines().map(String::toInt)
fun inOut(file: String = callerDay()): Triple<Grid, Int, Int> = output(file).let { Triple(input(file), it[0], it[1]) }

private val DAY = Regex("""(\d\d)""")
private fun callerDay(): String =
    StackWalker.getInstance().walk { s ->
        s.asSequence().firstNotNullOf { DAY.find(it.className) }.groupValues[1]
    }

private val NON_DIGITS = Regex("""\D+""")
fun String.numbers(separators: Regex = NON_DIGITS): List<Int> =
    split(separators).map(String::toInt)

fun Grid.numbers(separators: Regex = NON_DIGITS): Matrix<Int> =
    map { it.numbers(separators) }
