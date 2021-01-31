package v2016

class Day04 {
    companion object {
        fun part1(input: List<String>): Long {
            return input.mapNotNull {
                val (name, sectorId, checksum) = parse(it)
                if (checksum(name) == checksum) {
                    sectorId
                } else {
                    null
                }
            }.sum()
        }

        fun part2(input: List<String>): Long {
            val ids = input.mapNotNull {
                val (encryptedName, sectorId, checksum) = parse(it)
                if (checksum(encryptedName) == checksum) {
                    val name = decrypt(encryptedName, sectorId)
                    if (name.contains("northpole")) {
                        sectorId
                    } else {
                        null
                    }
                } else {
                    null
                }
            }
            return if (ids.isEmpty()) {
                -1L
            } else {
                ids.first()
            }
        }

        private fun decrypt(name: String, sectorId: Long): String {
            return name.map {
                if (it == '-') {
                    ' '
                } else {
                    // Caesar cypher (shift by sector ID)
                    ('a'.toInt() + (it.toInt() - 'a'.toInt() + sectorId) % 26).toChar()
                }
            }.joinToString("")
        }

        private fun checksum(name: String): String {
            return name.replace("-", "")
                .groupingBy { it }
                .eachCount()
                .map { it.value to it.key }
                .groupBy { it.first }
                .toSortedMap(compareByDescending { it })
                .map { it.value.map { (_, char) -> char }.sorted().joinToString("") }
                .joinToString("")
                .take(5)
        }

        private fun parse(room: String): Triple<String, Long, String> {
            val sectorIdIndex = room.lastIndexOf("-")
            val name = room.substring(0, sectorIdIndex)
            val sectorId = room.substring(sectorIdIndex + 1, room.length - 7).toLong()
            val checksum = room.substring(room.length - 6, room.length - 1)
            return Triple(name, sectorId, checksum)
        }
    }
}
