package com.arxict.aoc.year2024

class Day17(lines: List<String>) {
    data class Instruction(val code: Int, val operand: Long)
    data class Register(var A: Long = 0, var B: Long = 0, var C: Long = 0)

    val register = lines.subList(0, 3).map { it.substringAfter(": ").toLong() }.let { (A, B, C) ->
        Register(A, B, C)
    }
    val programNumbers = lines[4].substringAfter("Program: ").split(',').map { it.toInt() }
    val program = programNumbers.windowed(2, 2).map { (c, v) -> Instruction(c, v.toLong()) }

    fun Instruction.combo(register: Register) =
        with(register) {
            when (operand.toInt()) {
                0, 1, 2, 3 -> operand
                4 -> A
                5 -> B
                6 -> C
                else -> error("Wrong operand $operand")
            }
        }

    fun run(register: Register): Sequence<Long> = sequence {
        var ip = 0
        while (ip < program.size) {
            val instruction = program[ip]
            fun divA() = register.A / (1 shl instruction.combo(register).toInt())
            with(register) {
                when (instruction.code) {
                    0 -> A = divA()
                    1 -> B = B xor instruction.operand
                    2 -> B = (instruction.combo(register) % 8)
                    3 -> if (A != 0L) ip = instruction.operand.toInt().dec()
                    4 -> B = B xor C
                    5 -> yield(instruction.combo(register) % 8)
                    6 -> B = divA()
                    7 -> C = divA()
                }
                ip++
            }
        }
    }

    fun part1(): String =
        run(register).joinToString(",")

    fun part2(): Long {
        for (a in 1L..1_000_000) {
            var count = 0
            if (run(Register(a)).withIndex().all { (i, v) ->
                    count++
                    programNumbers[i].toLong() == v
                } && count == programNumbers.size) return a
        }
        error("Can't find it")
    }
}
/*
    fun part2(): Long {
        val map = buildMap {
            for (a in 0..<8*8)
                set(run(Register(a.toLong())).single().toInt(), a)
                set(run(Register(a.toLong())).toList().map { it.toInt() }, a)
        }.also (::println)
        val pair = programNumbers.windowed(2,2).also(::println)
        pair.forEach { println("$it ${map[it]}") }
         {5=2, 4=1, 7=3, 1=4, 0=5, 3=6, 2=7}
         [2, 4, 1, 2, 7, 5, 1, 7, 4, 4, 0, 3, 5, 5, 3, 0]
        val needed = listOf(1, 2, 7) // programNumbers//
        val input = needed.map { map[it] }.reversed().also(::println)
        val inputNum = input.joinToString("").toLong(8)
        val generated = run(Register(inputNum)).toList().map { it.toInt() }.also(::println)
        require(generated == needed)

        println(programNumbers)
        val x= programNumbers.joinToString("") { map[it].toString() }.toLong(8)
        println(run(Register(x)).toList())
        return 0
    }

    A = A/8 + A%8
                       A  B  C
    2 4   B = A % 8       A%8
    1 2   B = B xor 2     (A%8)&2
    7 5   C = A / 2^B         A/2^((A%8)&2)
    1 7   B = B xor 7     ((A%8)&2)&7
    4 4   B = B xor C     (((A%8)&2)&7) & (A/2^((A%8)&2))
    0 3   A = A / 8     A/8
    5 5   B mod 8        ((((A%8)&2)&7) & (A/2^((A%8)&2)))%8
    3 0   A==0 exit


    5 5   (((A % 8) xor 2) xor 7) xor (A / 2^((A % 8) xor 2)) mod 8      2 4 1 2 7 5
    3 0   A / 8==0 exit
     */

