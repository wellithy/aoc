import java.util.TreeSet

fun main() {
    fun solve(grid: Grid) = with(grid.iterator()) {
        val rule = buildMap {
            while (true)
                next().takeIf(String::isNotEmpty)?.numbers('|')?.let { (before, after) ->
                    getOrPut(before) { TreeSet() } += after
                } ?: break
        }

        var part1 = 0
        var part2 = 0
        while (hasNext()) {
            val pages = next().numbers(',')
            val sorted = pages.sortedWith { a, b ->
                rule[a]?.takeIf { b in it }?.let { -1 } ?: 1
            }
            val mid = sorted[pages.size / 2]
            if (pages == sorted) part1 += mid
            else part2 += mid
        }
        part1 to part2
    }


    inOut().let { (grid, one, two) ->
        solve(grid).let {
            check(it.first == one)
            check(it.second == two)
        }
    }
}
