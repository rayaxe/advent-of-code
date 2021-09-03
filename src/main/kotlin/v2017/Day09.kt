package v2017

class Day09 {
    private enum class Mode { SCORE_GROUPS, CLEAN_GARBAGE, IGNORE_GARBAGE }

    companion object {
        fun part1(input: String): Long {
            return run(input).first
        }

        fun part2(input: String): Long {
            return run(input).second
        }

        private fun run(input: String): Pair<Long, Long> {
            val stream = input.toMutableList()
            var groupScore = 0L
            var groupDepth = 0
            var garbageScore = 0L
            var mode = Mode.SCORE_GROUPS

            fun cleanGarbage(c: Char): Mode {
                return when (c) {
                    '!' -> Mode.IGNORE_GARBAGE
                    '>' -> Mode.SCORE_GROUPS
                    else -> {
                        garbageScore += 1
                        Mode.CLEAN_GARBAGE
                    }
                }
            }

            fun scoreGroups(c: Char): Mode {
                return when (c) {
                    '{' -> {
                        groupDepth += 1
                        Mode.SCORE_GROUPS
                    }
                    '}' -> {
                        groupScore += groupDepth
                        groupDepth -= 1
                        Mode.SCORE_GROUPS
                    }
                    '<' -> Mode.CLEAN_GARBAGE
                    ',' -> Mode.SCORE_GROUPS
                    else -> error("Unexpected char '$c' in mode=$mode")
                }
            }

            while (stream.isNotEmpty()) {
                val c = stream.removeFirst()
                mode = when (mode) {
                    Mode.IGNORE_GARBAGE -> Mode.CLEAN_GARBAGE
                    Mode.CLEAN_GARBAGE -> cleanGarbage(c)
                    Mode.SCORE_GROUPS -> scoreGroups(c)
                }
            }

            return groupScore to garbageScore
        }
    }
}
