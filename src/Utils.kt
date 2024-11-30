import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.reader

val TXT_DIR = Path(".txt")
val String.lines get() = TXT_DIR.resolve(this).reader().readLines()

@OptIn(ExperimentalStdlibApi::class)
fun String.digest(name: String) = with(MessageDigest.getInstance(name)) {
    toByteArray().let(::digest).toHexString().padStart(digestLength * 2, '0')
}

val String.md5 get() = digest("MD5")

fun Any?.println() = println(this)
