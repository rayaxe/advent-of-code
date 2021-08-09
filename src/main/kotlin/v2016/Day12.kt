package v2016

class Day12 {

    class Instruction(val operation: String, val x: String, val y: String)

    companion object {
        private val register = mutableMapOf("a" to 0L, "b" to 0L, "c" to 0L, "d" to 0L)

        fun part1(input: List<String>): Long {
            return run(input)
        }

        fun part2(input: List<String>): Long {
            register["c"] = 1
            return run(input)
        }

        private fun run(input: List<String>): Long {
            val instructions = input.map { parse(it) }
            var pointer = 0
            while (pointer < instructions.size) {
                val instruction = instructions[pointer]
                pointer += when (instruction.operation) {
                    "cpy" -> copy(instruction)
                    "inc" -> increase(instruction)
                    "dec" -> decrease(instruction)
                    "jnz" -> jumpNonZero(instruction)
                    else -> error("Unsupported operation: ${instruction.operation}")
                }
            }
            return register.getValue("a").toLong()
        }

        private fun copy(instruction: Instruction): Int {
            val value = instruction.x.toLongOrNull() ?: register.getValue(instruction.x)
            register[instruction.y] = value
            return 1
        }

        private fun increase(instruction: Instruction): Int {
            register[instruction.x] = register.getValue(instruction.x).toLong() + 1
            return 1
        }

        private fun decrease(instruction: Instruction): Int {
            register[instruction.x] = register.getValue(instruction.x).toLong() - 1
            return 1
        }

        private fun jumpNonZero(instruction: Instruction): Int {
            val value = instruction.x.toLongOrNull() ?: register.getValue(instruction.x)
            return if (value == 0L) 1 else instruction.y.toInt()
        }

        private fun parse(line: String): Instruction {
            val input = line.split(' ')
            val y = if (input.size == 3) input[2] else ""
            return Instruction(input[0], input[1], y)
        }
    }
}
