package com.arxict.aoc.year2015

import com.arxict.aoc.common.digitsRegex
import kotlinx.serialization.json.*


class Day12(lines: List<String>) {
    val record = lines.first()

    fun part1() = digitsRegex.findAll(record).sumOf { it.groupValues[1].toInt() }

    fun part2(): Int = Json.parseToJsonElement(record).sum()

    fun JsonElement.sum() = when (this) {
        is JsonArray -> sum()
        is JsonObject -> sum()
        is JsonPrimitive -> intOrNull ?: 0
    }

    fun JsonArray.sum(): Int =
        sumOf { it.sum() }

    fun JsonObject.sum(): Int =
        values.sumOf {
            if (it is JsonPrimitive && it.content == "red") return 0
            it.sum()
        }
}
