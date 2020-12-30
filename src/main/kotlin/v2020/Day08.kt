package v2020

private data class Instruction(val operation: String, val parameter: Int, var counter: Int = 0)

fun day08Part1(code: List<String>): Long {
    val instructions = code
        .map { parse(it) }
        .toList()
    return run(instructions).first
}

fun day08Part2(code: List<String>): Long {
    val instructions = code
        .map { parse(it) }
        .toList()
    for (i in instructions.indices) {
        val instruction = instructions[i]
        if (instruction.operation != "acc") {
            val result = run(mutate(instructions.toMutableList(), i))
            if (result.second) {
                return result.first
            }
        }
    }
    return -1
}

private fun mutate(instructions: MutableList<Instruction>, i: Int): MutableList<Instruction> {
    instructions.forEach { it.counter = 0 }
    val instruction = instructions[i]
    instructions[i] = Instruction(swap(instruction.operation), instruction.parameter)
    return instructions
}

private fun swap(operation: String): String {
    return if (operation == "jmp") "nop" else "jmp"
}

private fun run(instructions: List<Instruction>): Pair<Long, Boolean> {
    var pointer = 0
    var acc = 0L
    while (true) {
        if (pointer == instructions.size) {
            return Pair(acc, true)
        }
        val instruction = instructions[pointer]
        if (instruction.counter == 1) {
            return Pair(acc, false)
        }
        when (instruction.operation) {
            "acc" -> {
                acc += instruction.parameter
                pointer++
            }
            "jmp" -> pointer += instruction.parameter
            "nop" -> pointer++
            else -> throw IllegalStateException("Unexpected operation: '${instruction.operation}'")
        }
        instruction.counter++
    }
}

private fun parse(text: String): Instruction {
    val parts = text.split(" ")
    return Instruction(parts[0], parts[1].toInt())
}
