package com.arxict.aoc.year2015

import com.arxict.aoc.common.AdditiveGraph
import com.arxict.aoc.common.buildGraph
import com.arxict.aoc.common.travelingSalesmanProblem


class Day13(lines: List<String>) {
    val regex = Regex("""(.+) would (.+) (\d+) happiness units by sitting next to (.+).""")
    fun String.toInt(sign: String) = when (sign) {
        "gain" -> toInt()
        "lose" -> -toInt()
        else -> error("Unknown $sign")
    }

    val happiness = buildMap {
        lines.forEach {
            regex.find(it)!!.groupValues.let { (_, v1, sign, edge, v2) ->
                getOrPut(v1, ::mutableMapOf)[v2] = edge.toInt(sign)
            }
        }
    }

    fun best(map: Map<String, Map<String, Int>>) = buildGraph {
        map.forEach { (v1, m) ->
            m.forEach { (v2, w) ->
                (w + map.getValue(v2).getValue(v1)).let {
                    add(v1, v2, it, it)
                }
            }
        }
    }.let(::AdditiveGraph).travelingSalesmanProblem().second

    fun part1(): Int = best(happiness)

    fun part2(): Int {
        val withI = buildMap {
            putAll(happiness)
            with(happiness.keys) {
                set("I", associateWithTo(mutableMapOf()) { 0 })
                forEach { getValue(it)["I"] = 0 }
            }
        }
        return best(withI)
    }

}
