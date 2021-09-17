package v2017

import java.util.*

class Day16 {
    private interface Instruction
    private class Spin(val size: Int) : Instruction
    private class Exchange(val a: Int, val b: Int) : Instruction
    private class Partner(val a: Char, val b: Char) : Instruction

    companion object {
        fun part1(input: List<String>, numberOfPrograms: Int = 16): String {
            val instructions = input.map { parse(it) }
            return performDances(numberOfPrograms, instructions, 1)
        }

        fun part2(input: List<String>, numberOfPrograms: Int = 16): String {
            val instructions = input.map { parse(it) }
            return performDances(numberOfPrograms, instructions, 1_000_000)
        }

        private fun performDances(numberOfPrograms: Int, instructions: List<Instruction>, numberOfDances: Int): String {
            val programs = ('a'..'p').take(numberOfPrograms).toMutableList()
            val danceMoves = mutableListOf<String>()
            danceMoves.add(programs.joinToString(""))
            var i = 1
            while (true) {
                val danceMove = programs.dance(instructions)
                if (danceMove in danceMoves) {
                    break
                } else {
                    danceMoves.add(danceMove)
                    i++
                }
            }
            return danceMoves[numberOfDances % i]
        }

        private fun MutableList<Char>.dance(instructions: List<Instruction>): String {
            fun spin(size: Int) {
                Collections.rotate(this, size)
            }

            fun exchange(a: Int, b: Int) {
                this[a] = this[b].also { this[b] = this[a] }
            }

            fun partner(a: Char, b: Char) {
                exchange(this.indexOf(a), this.indexOf(b))
            }

            instructions.forEach {
                when (it) {
                    is Spin -> spin(it.size)
                    is Exchange -> exchange(it.a, it.b)
                    is Partner -> partner(it.a, it.b)
                }
            }

            return this.joinToString("")
        }

        private fun parse(instruction: String): Instruction {
            val params = instruction.drop(1)
            return when (instruction.first()) {
                's' -> Spin(params.toInt())
                'x' -> {
                    val (a, b) = params.split('/').map { it.toInt() }
                    Exchange(a, b)
                }
                'p' -> {
                    val (a, b) = params.split('/').map { it.first() }
                    Partner(a, b)
                }
                else -> error("Unexpected instruction '{${instruction.first()}}'")
            }
        }
    }
}
