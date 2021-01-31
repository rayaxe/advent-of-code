package v2016

class Day03 {
    companion object {
        fun part1(input: List<String>): Long {
            return input.map { parse(it) }
                .count { (a, b, c) -> isValid(a, b, c) }
                .toLong()
        }

        fun part2(input: List<String>): Long {
            return input.map { parse(it) }
                .chunked(3)
                .flatMap { transpose(it) }
                .count { (a, b, c) -> isValid(a, b, c) }
                .toLong()
        }

        private fun isValid(a: Int, b: Int, c: Int) = a + b > c && b + c > a && a + c > b

        private val whitespace = "\\s+".toRegex()
        private fun parse(triangle: String): Triple<Int, Int, Int> {
            val (a, b, c) = triangle.trim().split(whitespace)
            return Triple(a.toInt(), b.toInt(), c.toInt())
        }

        private fun transpose(triangles: List<Triple<Int, Int, Int>>): List<Triple<Int, Int, Int>> {
            val (a1, b1, c1) = triangles[0]
            val (a2, b2, c2) = triangles[1]
            val (a3, b3, c3) = triangles[2]
            return listOf(Triple(a1, a2, a3), Triple(b1, b2, b3), Triple(c1, c2, c3))
        }
    }
}
