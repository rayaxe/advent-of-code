package v2016

class Day21 {
    companion object {
        fun part1(password: String, operations: List<String>): String {
            return operations
                .map { it.split(" ") }
                .fold(password.toCharArray()) { acc, input ->
                    when (input[0] + " " + input[1]) {
                        "swap position" -> swapPosition(acc, input[2].toInt(), input[5].toInt())
                        "swap letter" -> swapLetter(acc, input[2][0], input[5][0])
                        "rotate left" -> rotateLeft(acc, input[2].toInt())
                        "rotate right" -> rotateRight(acc, input[2].toInt())
                        "rotate based" -> rotateBasedOn(acc, input[6][0])
                        "reverse positions" -> reverse(acc, input[2].toInt(), input[4].toInt())
                        "move position" -> move(acc, input[2].toInt(), input[5].toInt())
                        else -> error("Operation `${input[0]} ${input[1]}` not supported!")
                    }
                }
                .joinToString("")
        }

        fun part2(password: String, operations: List<String>): String {
            return operations
                .map { it.split(" ") }
                .reversed()
                .fold(password.toCharArray()) { acc, input ->
                    when (input[0] + " " + input[1]) {
                        "swap position" -> swapPosition(acc, input[5].toInt(), input[2].toInt())
                        "swap letter" -> swapLetter(acc, input[5][0], input[2][0])
                        "rotate left" -> rotateRight(acc, input[2].toInt())
                        "rotate right" -> rotateLeft(acc, input[2].toInt())
                        "rotate based" -> rotateBasedOnReversed(acc, input[6][0])
                        "reverse positions" -> reverse(acc, input[2].toInt(), input[4].toInt())
                        "move position" -> move(acc, input[5].toInt(), input[2].toInt())
                        else -> error("Operation `${input[0]} ${input[1]}` not supported!")
                    }
                }
                .joinToString("")
        }

        private fun swapPosition(word: CharArray, x: Int, y: Int): CharArray {
            word[x] = word[y].also { word[y] = word[x] }
            return word
        }

        private fun swapLetter(word: CharArray, x: Char, y: Char): CharArray {
            for (i in word.indices) {
                word[i] = when (word[i]) {
                    x -> y
                    y -> x
                    else -> word[i]
                }
            }
            return word
        }

        fun rotateLeft(word: CharArray, steps: Int): CharArray {
            return word.mapIndexed { index, _ -> word[(index + steps) % word.size] }.toCharArray()
        }

        fun rotateRight(word: CharArray, steps: Int): CharArray {
            return word.mapIndexed { index, _ -> word[(index + (word.size - steps % word.size)) % word.size] }
                .toCharArray()
        }

        fun rotateBasedOn(word: CharArray, c: Char): CharArray {
            val index = word.indexOf(c)
            val steps = if (index < 4) index + 1 else index + 2
            return rotateRight(word, steps)
        }

        fun rotateBasedOnReversed(word: CharArray, c: Char): CharArray {
            var input = word.copyOf()
            while (true) {
                input = rotateLeft(input, 1)
                if (rotateBasedOn(input, c).contentEquals(word)) {
                    return input
                }
            }
        }

        fun reverse(word: CharArray, x: Int, y: Int): CharArray {
            if (x >= y) error("Not supported!")
            var reversed = charArrayOf()
            if (x > 0) reversed += word.copyOfRange(0, x)
            reversed += word.copyOfRange(x, y + 1).reversedArray()
            if (y + 1 < word.size) reversed += word.copyOfRange(y + 1, word.size)
            return reversed
        }

        fun move(word: CharArray, x: Int, y: Int): CharArray {
            val sb: StringBuilder = StringBuilder(word.joinToString(""))
            sb.deleteAt(x)
            sb.insert(y, word[x])
            return sb.toString().toCharArray()
        }
    }
}
