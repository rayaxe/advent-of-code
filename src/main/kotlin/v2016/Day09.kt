package v2016

class Day09 {
    companion object {
        fun part1(input: String): Long {
            return length(input, false)
        }

        fun part2(input: String): Long {
            return length(input, true)
        }

        private fun length(file: String, includeNestedMarkers: Boolean): Long {
            val markerStart = file.indexOf('(')
            if (markerStart == -1) {
                return file.length.toLong()
            }
            val markerEnd = file.indexOf(')')
            val (length, times) = file.substring(markerStart + 1, markerEnd).split('x').map { it.toInt() }

            val startIndex = markerEnd + 1
            val endIndex = startIndex + length
            val repeated = file.substring(startIndex, endIndex)

            val repeatedLength = if (includeNestedMarkers) {
                length(repeated, includeNestedMarkers)
            } else {
                repeated.length.toLong()
            }

            return markerStart + times * repeatedLength + length(file.drop(endIndex), includeNestedMarkers)
        }
    }
}
