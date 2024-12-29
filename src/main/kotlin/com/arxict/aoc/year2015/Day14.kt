package com.arxict.aoc.year2015


class Day14(lines: List<String>) {
    val regex = Regex("""(.+) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds.""")

    data class Reindeer(val name: String, val speed: Int, val travel: Int, val rest: Int)

    fun Reindeer(line: String): Reindeer =
        regex.find(line)!!.groupValues.let { (_, name, speed, travel, rest) ->
            Reindeer(name, speed.toInt(), travel.toInt(), rest.toInt())
        }

    val reindeers = lines.map(::Reindeer)

    val Reindeer.cycle get() = travel + rest
    fun Reindeer.cycles(duration: Int) = duration / cycle
    fun Reindeer.travelingTime(duration: Int) = cycles(duration).let { cycles ->
        cycles * travel + (duration - cycles * cycle).coerceAtMost(travel)
    }

    fun Reindeer.travelingDistance(duration: Int) = travelingTime(duration) * speed

    fun part1() = reindeers.maxOf { it.travelingDistance(2503) }

    fun top(duration: Int, stars: IntArray) =
        with(reindeers.map { it.travelingDistance(duration) }) {
            val head = max()
            indices.forEach {
                if (this[it] == head) stars[it]++
            }
        }

    fun part2() =
        with(IntArray(reindeers.size)) {
            repeat(2503) {
                top(it + 1, this)
            }
            max()
        }
}
