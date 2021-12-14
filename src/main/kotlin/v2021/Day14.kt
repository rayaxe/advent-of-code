package v2021

class Day14 {
    companion object {
        fun part1(input: List<String>): Long {
            return insertElements(input, 10)
        }

        fun part2(input: List<String>): Long {
            return insertElements(input, 40)
        }

        private fun insertElements(input: List<String>, maxSteps: Int): Long {
            val (template, rules) = parse(input)
            val cache = mutableMapOf<Pair<String, Int>, Map<Char, Long>>()
            fun extend(pattern: String, step: Int): Map<Char, Long> {
                return if (cache.containsKey(pattern to step)) {
                    cache.getValue(pattern to step)
                } else {
                    val element = rules.getValue(pattern)
                    if (step < maxSteps) {
                        val left = extend("${pattern[0]}$element", step + 1)
                        val right = extend("$element${pattern[1]}", step + 1)
                        val result = left add right
                        cache[pattern to step] = result
                        result
                    } else {
                        mapOf(pattern[1] to 1L)
                    }
                }
            }
            return template
                .zipWithNext()
                .fold(mapOf(template.first() to 1L)) { acc, (left, right) -> acc add extend("$left$right", 0) }
                .let { it.values.maxOrNull()!! - it.values.minOrNull()!! }
        }

        private fun parse(input: List<String>): Pair<String, Map<String, Char>> {
            val template = input.first()
            val rules = input
                .drop(2)
                .associate { it.split(" -> ").let { (pattern, element) -> pattern to element.first() } }
            return template to rules
        }

        private infix fun Map<Char, Long>.add(other: Map<Char, Long>): Map<Char, Long> {
            return (this.keys.asSequence() + other.keys)
                .associateWith {
                    sequenceOf(this[it], other[it])
                        .filterNotNull()
                        .sum()
                }
        }
    }
}
