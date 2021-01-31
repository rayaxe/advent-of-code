package v2016

class Day02 {

    companion object {
        fun part1(input: List<String>): String {
            return solve(input, imaginedKeypad())
        }

        fun part2(input: List<String>): String {
            return solve(input, actualKeypad())
        }

        private fun solve(instructions: List<String>, keypad: Map<Char, Key>): String {
            return instructions.fold('5' to "") { (startKey, code), instruction ->
                val key = instruction.fold(startKey) { key, direction ->
                    with(keypad.getValue(key)) {
                        when (direction) {
                            'U' -> up
                            'R' -> right
                            'D' -> down
                            'L' -> left
                            else -> error("Unexpected direction: $direction")
                        }
                    }
                }
                key to code + key
            }.second
        }

        private data class Key(val up: Char, val right: Char, val down: Char, val left: Char)

        private fun imaginedKeypad(): Map<Char, Key> {
            val keypad = mutableMapOf<Char, Key>()
            keypad['1'] = Key('1', '2', '4', '1')
            keypad['2'] = Key('2', '3', '5', '1')
            keypad['3'] = Key('3', '3', '6', '2')
            keypad['4'] = Key('1', '5', '7', '4')
            keypad['5'] = Key('2', '6', '8', '4')
            keypad['6'] = Key('3', '6', '9', '5')
            keypad['7'] = Key('4', '8', '7', '7')
            keypad['8'] = Key('5', '9', '8', '7')
            keypad['9'] = Key('6', '9', '9', '8')
            return keypad.toMap()
        }

        private fun actualKeypad(): Map<Char, Key> {
            val keypad = mutableMapOf<Char, Key>()
            keypad['1'] = Key('1', '1', '3', '1')
            keypad['2'] = Key('2', '3', '6', '2')
            keypad['3'] = Key('1', '4', '7', '2')
            keypad['4'] = Key('4', '4', '8', '3')
            keypad['5'] = Key('5', '6', '5', '5')
            keypad['6'] = Key('2', '7', 'A', '5')
            keypad['7'] = Key('3', '8', 'B', '6')
            keypad['8'] = Key('4', '9', 'C', '7')
            keypad['9'] = Key('9', '9', '9', '8')
            keypad['A'] = Key('6', 'B', 'A', 'A')
            keypad['B'] = Key('7', 'C', 'D', 'A')
            keypad['C'] = Key('8', 'C', 'C', 'B')
            keypad['D'] = Key('B', 'D', 'D', 'D')
            return keypad.toMap()
        }
    }
}
