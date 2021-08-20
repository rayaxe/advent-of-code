package v2016

class Day25 {
    private data class Instruction(val operation: String, val x: String, val y: String)

    companion object {
        private val n = 100

        fun part1(input: List<String>): Long {
            val instructions = input.map { parse(it) }
            for (a in 0..Int.MAX_VALUE) {
                val register = mutableMapOf("a" to a, "b" to 0, "c" to 0, "d" to 0)
                val generatorSignal = run(instructions.toMutableList(), register)
                val clockSignal = generateSequence(0L, { (it + 1) % 2 })

                (generatorSignal zip clockSignal)
                    .take(n)
                    .toList()
                    .all { it.first == it.second }
                    .let { if (it) return a.toLong() }
            }
            error("No solution found")
        }

        fun part2(input: List<String>): Long {
            return -1L
        }

        private fun run(instructions: MutableList<Instruction>, register: MutableMap<String, Int>): Sequence<Long> {
            return sequence {
                var pointer = 0

                fun copy(instruction: Instruction) {
                    if (instruction.y.toIntOrNull() == null) {
                        val value = instruction.x.toIntOrNull() ?: register.getValue(instruction.x)
                        register[instruction.y] = value
                    }
                    pointer++
                }

                fun increase(instruction: Instruction) {
                    register[instruction.x] = register.getValue(instruction.x) + 1
                    pointer++
                }

                fun decrease(instruction: Instruction) {
                    register[instruction.x] = register.getValue(instruction.x) - 1
                    pointer++
                }

                fun jumpNonZero(instruction: Instruction) {
                    val value = instruction.x.toIntOrNull() ?: register.getValue(instruction.x)
                    pointer += if (value == 0) 1 else instruction.y.toIntOrNull() ?: register.getValue(instruction.y)
                }

                fun toggle(instruction: Instruction) {
                    val target = pointer + register.getValue(instruction.x)
                    if (target in 0 until instructions.size) {
                        val newOperation = with(instructions[target]) {
                            val hasOneArgument = y == ""
                            if (hasOneArgument) {
                                when (operation) {
                                    "inc" -> "dec"
                                    else -> "inc"
                                }
                            } else {
                                when (operation) {
                                    "jnz" -> "cpy"
                                    else -> "jnz"
                                }
                            }
                        }
                        instructions[target] = instructions[target].copy(operation = newOperation)
                    }
                    pointer++
                }

                suspend fun SequenceScope<Long>.out(instruction: Instruction) {
                    val value = instruction.x.toIntOrNull() ?: register.getValue(instruction.x)
                    yield(value.toLong())
                    pointer++
                }

                var i = 0
                while (pointer < instructions.size && i < n) {
                    val instruction = instructions[pointer]
                    when (instruction.operation) {
                        "cpy" -> copy(instruction)
                        "inc" -> increase(instruction)
                        "dec" -> decrease(instruction)
                        "jnz" -> jumpNonZero(instruction)
                        "tgl" -> toggle(instruction)
                        "out" -> {
                            out(instruction)
                            i++
                        }
                        else -> error("Unsupported operation: ${instruction.operation}")
                    }
                }
                yield(-1L)
            }
        }

        private fun parse(line: String): Instruction {
            val input = line.split(' ')
            val y = if (input.size == 3) input[2] else ""
            return Instruction(input[0], input[1], y)
        }
    }
}
