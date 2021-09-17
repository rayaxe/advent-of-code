package v2017

class Day18 {
    companion object {
        fun part1(input: List<String>): Long {
            val registers = mutableMapOf<Char, Long>()
            val instructions = input.map { it.split(' ') }

            fun String.getValue(): Long {
                return this.toLongOrNull() ?: registers.getValue(this.first())
            }

            var pointer = 0L
            val sounds = mutableListOf<Long>()
            while (pointer in instructions.indices) {
                val instruction = instructions[pointer.toInt()]
                val operation = instruction[0]
                val register = instruction[1].first()
                val registerValue = registers.getOrDefault(register, 0)
                when (operation) {
                    "snd" -> sounds.add(registers.getValue(register))
                    "set" -> registers[register] = instruction[2].getValue()
                    "add" -> registers[register] = registerValue + instruction[2].getValue()
                    "mul" -> registers[register] = registerValue * instruction[2].getValue()
                    "mod" -> registers[register] = registerValue % instruction[2].getValue()
                    "rcv" -> {
                        if (registerValue != 0L) {
                            registers[register] = registerValue
                            return sounds.last().toLong()
                        }
                    }
                    "jgz" -> {
                        if (registerValue > 0L) {
                            pointer += instruction[2].getValue() - 1
                        }
                    }
                    else -> error("Unsupported operation: $operation")
                }
                pointer += 1
            }
            return -1L
        }

        fun part2(input: List<String>): Long {
            return -1L
        }
    }
}
