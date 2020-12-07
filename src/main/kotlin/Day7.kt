private data class Bag(val number: Int, val color: String)

private const val SHINY_GOLD = "shiny gold"

fun day7Part1(rules: List<String>): Long {
    val rulesMap = parse(rules)
    return rulesMap
        .count { (_, values) -> containsAnyShinyGold(rulesMap, values) }
        .toLong()
}

fun day7Part2(rules: List<String>): Long {
    val rulesMap = parse(rules)
    val value = rulesMap.getValue(SHINY_GOLD)
    return sumBags(rulesMap, value)
}

private fun sumBags(rulesMap: Map<String, Set<Bag>>, values: Set<Bag>): Long {
    if (values.isEmpty()) {
        return 0L
    }
    var sum = 0L
    for (value in values) {
        sum += value.number + value.number * sumBags(rulesMap, rulesMap.getValue(value.color))
    }
    return sum
}

private fun containsAnyShinyGold(rulesMap: Map<String, Set<Bag>>, values: Set<Bag>): Boolean {
    if (values.isEmpty()) {
        return false
    }
    for (value in values) {
        if (value.color == SHINY_GOLD) {
            return true
        } else if (rulesMap.containsKey(value.color) && containsAnyShinyGold(
                rulesMap,
                rulesMap.getValue(value.color)
            )
        ) {
            return true
        }
    }
    return false
}

private fun parse(rules: List<String>): Map<String, Set<Bag>> {
    return rules
        .map { parse(it) }
        .toMap()
}

private fun parse(rule: String): Pair<String, Set<Bag>> {
    val keyAndValues = rule
        .replace(" bags", "")
        .replace(" bag", "")
        .replace(".", "")
        .split(" contain ")
    val key = keyAndValues[0]
    val values = keyAndValues[1]
    var bags = mutableSetOf<Bag>()
    if (!values.equals("no other")) {
        bags = values
            .split(", ")
            .map { Bag(Character.getNumericValue(it[0]), it.substring(2)) }
            .toMutableSet()
    }
    return Pair(key, bags)
}
