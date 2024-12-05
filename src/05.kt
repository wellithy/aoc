fun main() {
    fun solve(grid: Grid) = with(grid.iterator()) {
        val rule = buildMap {
            while (true)
                next().takeIf(String::isNotEmpty)?.numbers("|")?.let { (before, after) ->
                    getOrPut(before, ::mutableListOf) += after
                } ?: break
        }

        fun List<Int>.valid(index: Int): Boolean =
            rule[get(index)]?.containsAll(subList(index.inc(), size)) == true

        fun correct(pages: List<Int>) = pages.sortedWith { a, b ->
            rule[a]?.takeIf { b in it }?.let { -1 } ?: 1
        }

        var part1 = 0
        var part2 = 0
        while (hasNext()) {
            val pages = next().numbers(",")
            if (pages.indices.all(pages::valid)) part1 += pages[pages.size / 2]
            else part2 += correct(pages)[pages.size / 2]
        }
        part1 to part2
    }


    inOut("05").let { (grid, one, two) ->
        solve(grid).let {
            check(it.first == one)
            check(it.second == two)
        }
    }
}
