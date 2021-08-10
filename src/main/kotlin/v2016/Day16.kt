package v2016

class Day16 {
    companion object {
        fun part1(input: String, length: Int): String {
            val data = generateData(input, length)
            return checkSum(data)
        }

        fun part2(input: String, length: Int): String {
            return part1(input, length)
        }

        private fun checkSum(data: String): String {
            var checksum = data
            do {
                checksum = checksum
                    .windowed(2, 2)
                    .joinToString("") {
                        when (it[0] == it[1]) {
                            true -> "1"
                            else -> "0"
                        }
                    }
            } while (checksum.length % 2 == 0)
            return checksum
        }

        fun generateData(input: String, length: Int): String {
            var data = input
            while (data.length < length) {
                data = data + '0' + data.reversed().map {
                    when (it) {
                        '1' -> '0'
                        else -> '1'
                    }
                }.joinToString("")
            }
            return data.substring(0, length)
        }
    }
}
