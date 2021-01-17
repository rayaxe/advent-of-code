package v2015

class Day23 {
    private data class Instruction(val operation: String, val register: String, val offset: Int = 0)

    companion object {
        fun part1(input: List<String>): Long {
            return run(input).getValue("b")
        }

        fun part2(input: List<String>): Long {
            return run(input, 1L).getValue("b")
        }

        fun run(input: List<String>, valueA: Long = 0L): MutableMap<String, Long> {
            val program = parse(input)
            val registerMap = mutableMapOf<String, Long>()
            registerMap["a"] = valueA
            registerMap["b"] = 0L
            var pointer = 0
            while (true) {
                if (!program.containsKey(pointer)) {
                    break
                }
                val instruction = program.getValue(pointer)
                with(instruction) {
                    when (operation) {
                        "hlf" -> {
                            registerMap[register] = registerMap.getValue(register) / 2; pointer++
                        }
                        "tpl" -> {
                            registerMap[register] = registerMap.getValue(register) * 3; pointer++
                        }
                        "inc" -> {
                            registerMap[register] = registerMap.getValue(register) + 1; pointer++
                        }
                        "jmp" -> pointer += offset
                        "jie" -> if (registerMap.getValue(register) % 2 == 0L) pointer += offset else pointer++
                        "jio" -> if (registerMap.getValue(register) == 1L) pointer += offset else pointer++
                        else -> error("Unsupported operation: $operation")
                    }
                }
            }
            return registerMap
        }

        private fun parse(input: List<String>): Map<Int, Instruction> {
            return input.mapIndexed { index, instruction ->
                val operation = instruction.substring(0, 3)
                val rest = instruction.substring(4)
                index to when (operation) {
                    "hlf", "tpl", "inc" -> Instruction(operation, rest)
                    "jmp" -> Instruction(operation, "", rest.toInt())
                    "jie", "jio" -> {
                        val (r, offset) = rest.split(", ")
                        Instruction(operation, r, offset.toInt())
                    }
                    else -> error("Unsupported operation: $operation")
                }
            }.toMap()
        }
    }
}
