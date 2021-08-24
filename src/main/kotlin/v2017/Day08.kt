package v2017

class Day08 {
    companion object {
        fun part1(input: List<String>): Long {
            val (register, _) = solve(input)
            return register.values.maxOrNull()!!.toLong()
        }

        fun part2(input: List<String>): Long {
            val (_, max) = solve(input)
            return max.toLong()
        }

        private fun solve(input: List<String>): Pair<MutableMap<String, Int>, Int> {
            val register = mutableMapOf<String, Int>()
            var max = 0

            fun updateRegister(key: String, operation: (Int, Int) -> Int, value: Int) {
                val newValue = operation(register.getOrDefault(key, 0), value)
                if (newValue > max) max = newValue
                register[key] = newValue
            }

            fun validate(condition: Triple<String, String, Int>): Boolean {
                val (key, operator, value) = condition
                val registerValue = register.getOrDefault(key, 0)
                return when (operator) {
                    ">" -> registerValue > value
                    ">=" -> registerValue >= value
                    "<" -> registerValue < value
                    "<=" -> registerValue <= value
                    "==" -> registerValue == value
                    "!=" -> registerValue != value
                    else -> error("Operator '$operator' not supported")
                }
            }

            fun execute(operation: Triple<String, String, Int>) {
                val (key, operator, value) = operation
                val op: (Int, Int) -> Int = when (operator) {
                    "inc" -> Int::plus
                    "dec" -> Int::minus
                    else -> error("Operation '$operator' not supported")
                }
                updateRegister(key, op, value)
            }

            input
                .map { parse(it) }
                .forEach { (operation, condition) -> if (validate(condition)) execute(operation) }

            return register to max
        }

        private val instructionRegex = "([a-z]+) ([a-z]+) (-?[0-9]+) if ([a-z]+) (.*) (-?[0-9]+)".toRegex()

        private fun parse(text: String): Pair<Triple<String, String, Int>, Triple<String, String, Int>> {
            return instructionRegex.matchEntire(text)
                ?.destructured
                ?.let { (r1, o1, v1, r2, o2, v2) -> Triple(r1, o1, v1.toInt()) to Triple(r2, o2, v2.toInt()) }
                ?: throw IllegalArgumentException("Bad input '$text'")
        }
    }
}
