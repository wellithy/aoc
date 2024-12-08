import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.streams.asSequence
import kotlin.text.split

fun readLines(dir: String, file: String = callerDay()) = Path(dir, file).readLines()
fun input() = readLines("in")
fun output() = readLines("out")
fun test() = readLines("test")

private val DAY = Regex("""(\d\d)""")
private fun callerDay(): String =
    StackWalker.getInstance().walk { s ->
        s.asSequence().firstNotNullOf { DAY.find(it.className) }.groupValues[1]
    }

private val NON_DIGITS = Regex("""\D+""")
fun String.numbers() = trim().split(NON_DIGITS)
fun String.integers() = numbers().map(String::toInt)
fun <T> List<T>.toPair() = (get(0) to get (1)).also{require(size == 2)}
