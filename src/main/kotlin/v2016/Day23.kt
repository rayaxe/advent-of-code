package v2016

class Day23 {
    private data class Instruction(val operation: String, val x: String, val y: String)

    companion object {
        fun part1(input: List<String>): Long {
            val instructions = input.map { parse(it) }
            return run(instructions.toMutableList(), 7)
        }

        fun part2(input: List<String>): Long {
            val instructions = input.map { parse(it) }
            return run(instructions.toMutableList(), 12)
        }

        private fun run(instructions: MutableList<Instruction>, initialValue: Int = 0): Long {
            val register = mutableMapOf("a" to initialValue, "b" to 0, "c" to 0, "d" to 0)

            fun copy(instruction: Instruction): Int {
                if (instruction.y.toLongOrNull() != null) {
                    return 1
                }
                val value = instruction.x.toIntOrNull() ?: register.getValue(instruction.x)
                register[instruction.y] = value
                return 1
            }

            fun increase(instruction: Instruction): Int {
                register[instruction.x] = register.getValue(instruction.x) + 1
                return 1
            }

            fun decrease(instruction: Instruction): Int {
                register[instruction.x] = register.getValue(instruction.x) - 1
                return 1
            }

            fun jumpNonZero(instruction: Instruction): Int {
                val value = instruction.x.toIntOrNull() ?: register.getValue(instruction.x)
                return if (value == 0) 1 else instruction.y.toIntOrNull() ?: register.getValue(instruction.y)
            }

            var pointer = 0
            while (pointer < instructions.size) {
                val instruction = instructions[pointer]
                pointer += when (instruction.operation) {
                    "cpy" -> copy(instruction)
                    "inc" -> increase(instruction)
                    "dec" -> decrease(instruction)
                    "jnz" -> jumpNonZero(instruction)
                    "tgl" -> toggle(instructions, pointer + register.getValue(instruction.x))
                    else -> error("Unsupported operation: ${instruction.operation}")
                }
            }
            return register.getValue("a").toLong()
        }

        private fun toggle(instructions: MutableList<Instruction>, target: Int): Int {
            if (target !in 0 until instructions.size) {
                return 1
            }

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
            return 1
        }

        private fun parse(line: String): Instruction {
            val input = line.split(' ')
            val y = if (input.size == 3) input[2] else ""
            return Instruction(input[0], input[1], y)
        }
    }
}
