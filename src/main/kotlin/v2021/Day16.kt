package v2021

class Day16 {
    private sealed class Packet {
        abstract fun evaluate(): Long
    }

    private data class Number(val version: Long, val value: Long) : Packet() {
        override fun evaluate(): Long = this.value
    }

    private data class Operator(val version: Long, val type: Long, val packets: List<Packet>) : Packet() {
        override fun evaluate(): Long {
            return when (type) {
                0L -> packets.sumOf { it.evaluate() }
                1L -> packets.fold(1L) { acc, packet -> acc * packet.evaluate() }
                2L -> packets.minOf { it.evaluate() }
                3L -> packets.maxOf { it.evaluate() }
                5L -> packets.let { (a, b) -> if (a.evaluate() > b.evaluate()) 1L else 0L }
                6L -> packets.let { (a, b) -> if (a.evaluate() < b.evaluate()) 1L else 0L }
                7L -> packets.let { (a, b) -> if (a.evaluate() == b.evaluate()) 1L else 0L }
                else -> error("Unexpected type: $type")
            }
        }
    }

    companion object {
        fun part1(input: String): Long {
            val bits = convertHexStringToBits(input).toMutableList()
            return parsePacket(bits).sumVersions()
        }

        fun part2(input: String): Long {
            val bits = convertHexStringToBits(input).toMutableList()
            return parsePacket(bits).evaluate()
        }

        private fun Packet.sumVersions(): Long {
            return when (this) {
                is Number -> this.version
                is Operator -> this.version + this.packets.sumOf { it.sumVersions() }
            }
        }

        private fun parsePacket(bits: MutableList<Char>): Packet {
            val version = bits.removeAndReadAsLong(3)
            val type = bits.removeAndReadAsLong(3)
            return if (type == 4L) {
                val literalValue = parseLiteralValue(bits)
                Number(version, literalValue)
            } else {
                val packets = when (val lengthTypeId = bits.removeAndRead(1).first()) {
                    '0' -> parseBitLengthOfPackets(bits)
                    '1' -> parseNumberOfPackets(bits)
                    else -> error("Unexpected length type ID: $lengthTypeId")
                }
                Operator(version, type, packets)
            }
        }

        private fun parseNumberOfPackets(bits: MutableList<Char>): List<Packet> {
            val numberOfPackets = bits.removeAndReadAsLong(11)
            return (0 until numberOfPackets).map { parsePacket(bits) }
        }

        private fun parseBitLengthOfPackets(bits: MutableList<Char>): List<Packet> {
            val lengthInBitsOfPackets = bits.removeAndReadAsLong(15).toInt()
            val packetsBits = bits.removeAndRead(lengthInBitsOfPackets).toMutableList()
            val packets = mutableListOf<Packet>()
            while (packetsBits.isNotEmpty()) {
                packets.add(parsePacket(packetsBits))
            }
            return packets
        }

        private fun parseLiteralValue(bits: MutableList<Char>): Long {
            val valueBits = mutableListOf<Char>()
            var keepReading = true
            while (keepReading) {
                val group = bits.removeAndRead(5)
                valueBits.addAll(group.drop(1))
                if (group.first() == '0') {
                    keepReading = false
                }
            }
            return valueBits.joinToString("").toLong(2)
        }

        private fun MutableList<Char>.removeAndRead(n: Int): List<Char> =
            (0 until n).map { this.removeAt(0) }

        private fun MutableList<Char>.removeAndReadAsLong(n: Int): Long =
            this.removeAndRead(n).joinToString("").toLong(2)

        private fun convertHexStringToBits(input: String): List<Char> {
            return input.flatMap { convertHexCharToBits(it).toList() }
        }

        private fun convertHexCharToBits(hex: Char): String {
            return when (hex) {
                '0' -> "0000"
                '1' -> "0001"
                '2' -> "0010"
                '3' -> "0011"
                '4' -> "0100"
                '5' -> "0101"
                '6' -> "0110"
                '7' -> "0111"
                '8' -> "1000"
                '9' -> "1001"
                'A' -> "1010"
                'B' -> "1011"
                'C' -> "1100"
                'D' -> "1101"
                'E' -> "1110"
                'F' -> "1111"
                else -> error("Unexpected hex character: $hex")
            }
        }
    }
}
