fun day19Part1(input: List<String>): Long {
    val (ruleMap, messages) = parse(input)
    return messages.map { message -> match(ruleMap, "0", message) }
        .map { if (it) 1L else 0L }
        .sum()
}

fun day19Part2(input: List<String>): Long {
    val (ruleMap, messages) = parse(input)
    val mutableRuleMap = ruleMap.toMutableMap()
    mutableRuleMap["8"] = listOf(listOf("42"), listOf("42", "8"))
    mutableRuleMap["11"] = listOf(listOf("42", "31"), listOf("42", "11", "31"))
    return -1L
//    return messages.map { message -> match(mutableRuleMap, "0", message) }
//        .map { if (it) 1L else 0L }
//        .sum()
}

private fun match(ruleMap: Map<String, List<List<String>>>, currentRule: String, message: String): Boolean {
    val result = matchRecursive(ruleMap, currentRule, message, 0)
    val equalLength = result.second == message.length
    return result.first && equalLength
}

private fun matchRecursive(
    ruleMap: Map<String, List<List<String>>>,
    currentRule: String,
    message: String,
    messageIndex: Int
): Pair<Boolean, Int> {
    skip@ for (subRule in ruleMap.getValue(currentRule)) {
        var index = messageIndex
        for (ruleKey in subRule) {
            if (ruleKey.toIntOrNull() == null) {
                val matchA = ruleKey == "a" && message[index] == 'a'
                val matchB = ruleKey == "b" && message[index] == 'b'
                if (matchA || matchB) {
                    index++
                } else {
                    continue@skip
                }
            } else {
                val result = matchRecursive(ruleMap, ruleKey, message, index)
                if (result.first) {
                    index = result.second
                } else {
                    continue@skip
                }
            }
        }
        return Pair(true, index)
    }
    return Pair(false, -1)
}

private fun parse(input: List<String>): Pair<Map<String, List<List<String>>>, List<String>> {
    val emptyLineIndex = input.withIndex().find { it.value.isEmpty() }!!.index
    val rules = input.subList(0, emptyLineIndex)
        .map { it.replace("\"", "") }
        .map { parseRule(it) }
        .toMap()
    return Pair(rules, input.subList(emptyLineIndex + 1, input.size))
}

private fun parseRule(input: String): Pair<String, List<List<String>>> {
    val rule = input.split(": ")
    val ruleKey = rule[0]
    val ruleValues = rule[1].split(" | ").map { it.split(" ") }
    return ruleKey to ruleValues
}
