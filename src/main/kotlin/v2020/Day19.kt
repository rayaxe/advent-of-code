package v2020

private abstract class Rule
private class Rules(var rules: List<List<Int>>) : Rule()
private class Character(var char: Char) : Rule()

fun day19Part1(input: List<String>): Long {
    val (rules, messages) = parseInput(input)
    return messages.count { matches(rules, listOf(0), it) }.toLong()
}

fun day19Part2(input: List<String>): Long {
    val (rules, messages) = parseInput(input)
    rules[8] = Rules(listOf(listOf(42), listOf(42, 8)))
    rules[11] = Rules(listOf(listOf(42, 31), listOf(42, 11, 31)))
    return messages.count { matches(rules, listOf(0), it) }.toLong()
}

private fun parseInput(input: List<String>): Pair<MutableMap<Int, Rule>, List<String>> {
    val newlineIndex = input.withIndex().find { it.value.isEmpty() }!!.index
    val rules = input.subList(0, newlineIndex).map { parseRule(it) }.toMap().toMutableMap()
    val messages = input.subList(newlineIndex + 1, input.size)
    return Pair(rules, messages)
}

private fun parseRule(input: String): Pair<Int, Rule> {
    val (key, value) = input.split(": ").let { it[0].toInt() to it[1] }
    return if (value.startsWith('"')) {
        key to Character(value[1])
    } else {
        val rules = value.split(" | ").map { it.split(" ").map { ref -> ref.toInt() } }
        key to Rules(rules)
    }
}

private fun matches(rules: Map<Int, Rule>, rulesToMatch: List<Int>, message: String): Boolean {
    return if (rulesToMatch.isEmpty()) {
        message.isEmpty()
    } else {
        when (val rule = rules.getValue(rulesToMatch.first())) {
            is Character -> {
                message.startsWith(rule.char) && matches(rules, rulesToMatch.drop(1), message.drop(1))
            }
            is Rules -> {
                rule.rules.firstOrNull { matches(rules, it + rulesToMatch.drop(1), message) } != null
            }
            else -> false
        }
    }
}
