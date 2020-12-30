package v2020

import kotlin.streams.toList

private val REQUIRED_FIELDS = setOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
private val HEIGHT_FORMAT = "([0-9]+)(cm|in)".toRegex()
private val HAIR_COLOR_FORMAT = "#[0-9a-f]{6}".toRegex()
private val EYE_COLORS = setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

private data class Field(val key: String, val value: String)

fun day04Part1(passports: List<String>): Long {
    val validate = { fields: Set<Field> ->
        fields.stream()
            .map { it.key }
            .toList()
            .containsAll(REQUIRED_FIELDS)
    }
    return validate(passports, validate)
}

fun day04Part2(passports: List<String>): Long {
    val validate = { fields: Set<Field> ->
        REQUIRED_FIELDS.size == fields.stream()
            .filter { it.key != "cid" }
            .filter { validateField(it) }
            .count().toInt()
    }
    return validate(passports, validate)
}

private fun validate(passports: List<String>, validate: (Set<Field>) -> Boolean): Long {
    var validCount = 0L
    var current = mutableSetOf<Field>()
    for (passportLine in passports) {
        if (passportLine.isEmpty()) {
            if (validate(current)) {
                validCount++
            }
            current = mutableSetOf()
        } else {
            current.addAll(parseFields(passportLine))
        }
    }

    // Validate last entry
    if (validate(current)) {
        validCount++
    }

    return validCount
}

private fun validateField(field: Field): Boolean {
    with(field) {
        return when (key) {
            "byr" -> value.toInt() in 1920..2002
            "iyr" -> value.toInt() in 2010..2020
            "eyr" -> value.toInt() in 2020..2030
            "hgt" -> validateHeight(value)
            "hcl" -> HAIR_COLOR_FORMAT.matches(value)
            "ecl" -> EYE_COLORS.contains(value)
            "pid" -> value.length == 9 && value.toIntOrNull() != null
            else -> true
        }
    }
}

fun validateHeight(textValue: String): Boolean {
    return HEIGHT_FORMAT.matchEntire(textValue)
        ?.destructured
        ?.let { (value, unit) ->
            when (unit) {
                "cm" -> value.toInt() in 150..193
                "in" -> value.toInt() in 59..76
                else -> false
            }
        }
        ?: false
}

private fun parseFields(passportLine: String): List<Field> {
    return passportLine.split(" ")
        .map { it.split(":") }
        .map { Field(it[0], it[1]) }
        .toList()
}
