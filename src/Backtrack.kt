// 10
interface Backtrack<T> {
    fun accept(candidate: T): Boolean
    fun first(candidate: T): T?
    fun next(candidate: T): T?
}

fun <T> Backtrack<T>.solve(root: T): Sequence<T> = sequence {
    val backtrack = this@solve
    val dq = ArrayDeque<T>().apply { add(root) }
    while (dq.isNotEmpty()) {
        val candidate = dq.removeFirst()
        if (backtrack.accept(candidate)) yield(candidate)
        backtrack.first(candidate)?.let {
            var test = it
            while (true) {
                dq.addLast(test)
                test = backtrack.next(test) ?: break
            }
        }
    }
}
