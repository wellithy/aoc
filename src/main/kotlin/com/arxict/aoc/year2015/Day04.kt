package com.arxict.aoc.year2015
import java.security.MessageDigest

class Day04(lines: List<String>) {
    val input = lines.first()

    val md5 = MessageDigest.getInstance("MD5")

    @OptIn(ExperimentalStdlibApi::class)
    fun String.md5(): String = md5.digest(toByteArray()).toHexString()

    fun solve(prefix: String): Int =
        generateSequence(0, Int::inc).first {
            "$input$it".md5().startsWith(prefix)
        }
}
