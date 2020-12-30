package v2020

private val rulesRegex = "(.*): ([0-9]+)-([0-9]+) or ([0-9]+)-([0-9]+)".toRegex()

fun day16Part1(input: List<String>): Long {
    val notes = parse(input)
    val rulesFlattened = notes.first.map { listOf(it.second, it.third) }.flatten()
    val nearbyTickets = notes.third.flatten()
    return nearbyTickets
        .filter { number -> !rulesFlattened.any { it.contains(number) } }
        .sum().toLong()
}

fun day16Part2(input: List<String>): Long {
    val notes = parse(input)
    val rulesMap = notes.first.associateBy({ it.first }, { listOf(it.second, it.third) }).toMutableMap()
    val rulesFlattened = notes.first.map { listOf(it.second, it.third) }.flatten()
    val yourTicket = notes.second
    val nearbyTickets = notes.third
    val validTickets = nearbyTickets
        .filter { !it.any { number -> !rulesFlattened.any { rule -> rule.contains(number) } } }
    val fields = mutableListOf<MutableSet<String>>()
    repeat(yourTicket.size) { fields.add(rulesMap.keys.toMutableSet()) }
    validTickets.forEach { list ->
        yourTicket.forEachIndexed { index, _ ->
            rulesMap.forEach { (fieldName, validRanges) ->
                if (!validRanges.any { validRange -> validRange.contains(list[index]) }) {
                    fields[index].remove(fieldName)
                }
            }
        }
    }
    var i = 0
    while (i < 1000) {
        val knownFields = fields
            .filter { it.size == 1 }
            .map { it.first() }

        // Remove known fields from other positions
        repeat(fields.size) {
            fields.mapIndexed { index, mutableSet -> Pair(index, mutableSet) }
                .filter { it.second.size > 1 }
                .forEach { field -> knownFields.forEach { fields[field.first].remove(it) } }
        }

        // Stop loop when all fields are known
        if (fields.map { it.size }.maxByOrNull { it }!! == 1) {
            break
        }
        i++
    }

    return yourTicket.zip(fields.map { it.first() })
        .filter { it.second.startsWith("departure") }
        .fold(1L) { acc, pair -> acc * pair.first }
}

private fun parse(input: List<String>): Triple<List<Triple<String, IntRange, IntRange>>, List<Int>, List<List<Int>>> {
    val emptyLineIndices = input.withIndex()
        .filter { it.value.isEmpty() }
        .map { it.index }
    val rules = input.subList(0, emptyLineIndices[0]).map { parseRule(it) }
    val yourTicket = parseTickets(input.subList(emptyLineIndices[0] + 2, emptyLineIndices[1])).first()
    val nearbyTickets = parseTickets(input.subList(emptyLineIndices[1] + 2, input.size))
    return Triple(rules, yourTicket, nearbyTickets)
}

private fun parseRule(text: String): Triple<String, IntRange, IntRange> {
    return rulesRegex.matchEntire(text)
        ?.destructured
        ?.let { (field, a1, a2, b1, b2) -> Triple(field, a1.toInt()..a2.toInt(), b1.toInt()..b2.toInt()) }
        ?: throw IllegalArgumentException("Bad input '$text'")
}

private fun parseTickets(input: List<String>): List<List<Int>> {
    return input.map { it.split(',').map { value -> value.toInt() } }
}
