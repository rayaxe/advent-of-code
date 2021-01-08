package v2015

class Day19 {
    companion object {
        fun part1(input: List<String>): Long {
            val (replacementsMap, elements) = parse(input)
            val generated = mutableSetOf<String>()
            for (i in elements.indices) {
                val element = elements[i]
                if (replacementsMap.containsKey(element)) {
                    replacementsMap.getValue(element).forEach {
                        val molecule = elements.toMutableList()
                        molecule[i] = it
                        generated.add(molecule.joinToString(""))
                    }
                }
            }
            return generated.count().toLong()
        }

        fun part2(input: List<String>): Long {
            val (_, elements) = parse(input)
            return elements.count { it != "Rn" && it != "Ar" } - elements.count { it == "Y" } * 2 - 1L
        }

        private fun parse(input: List<String>): Pair<Map<String, List<String>>, MutableList<String>> {
            val splitIndex = input.indexOf("")
            val replacements = input.subList(0, splitIndex)
            val molecule = input.subList(splitIndex + 1, splitIndex + 2).first()
            return parseReplacements(replacements) to parseElements(molecule)
        }

        private fun parseReplacements(replacements: List<String>): Map<String, List<String>> {
            return replacements.map {
                val (from, to) = it.split(" => ")
                from to to
            }.groupBy({ it.first }, { it.second })
        }

        private fun parseElements(molecule: String): MutableList<String> {
            val elements = mutableListOf<String>()
            var i = 0
            while (i < molecule.length) {
                var element = "" + molecule[i]
                if (i + 1 < molecule.length && molecule[i + 1].isLowerCase()) {
                    element += molecule[i + 1]
                    i++
                }
                elements.add(element)
                i++
            }
            return elements
        }
    }
}
