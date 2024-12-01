import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.inputStream
import kotlin.io.path.readLines
import kotlin.io.path.reader

val TXT_DIR = Path("input")
fun lines(file: String) = TXT_DIR.resolve(file).readLines()

@OptIn(ExperimentalStdlibApi::class)
fun digest(str: String, name: String = "MD5") = with(MessageDigest.getInstance(name)) {
    str.toByteArray().let(::digest).toHexString().padStart(digestLength * 2, '0')
}

fun Any?.println() = println(this)
