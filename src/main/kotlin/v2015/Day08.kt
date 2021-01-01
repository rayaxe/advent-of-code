package v2015

class Day08 {

    companion object {
        private val asciiCharRegex = """(\\x[a-f0-9]{2})""".toRegex()

        fun part1(list: List<String>): Long {
            return list.map {
                it.length.toLong() - it.drop(1).dropLast(1)
                    .replace(asciiCharRegex, "1")
                    .replace("\\\\", "1")
                    .replace("\\\"", "1")
                    .length
            }.sum()
        }

        fun part2(list: List<String>): Long {
            val surroundingDoubleQuotes = 4L
            return list.map {
                it.replace(asciiCharRegex, "12345")
                    .replace("\\\\", "1234")
                    .replace("\\\"", "1234")
                    .length + surroundingDoubleQuotes - it.length.toLong()
            }.sum()
        }
    }
}
