package v2016

class Day08 {
    companion object {
        fun run(input: List<String>, screenWidth: Int = 50, screenHeight: Int = 6): Long {
            val screen = Array(screenHeight) { Array(screenWidth) { '.' } }
            input.forEach {
                val operation = parse(it)
                update(screen, operation)
            }
            display(screen)
            return screen.fold(0L) { acc, row -> acc + row.count { it == '#' } }
        }

        private fun display(screen: Array<Array<Char>>) {
            screen.forEach { row -> row.forEach { print(it) }; println() }
        }

        private fun update(screen: Array<Array<Char>>, operation: Triple<Operation, Int, Int>) {
            when (operation.first) {
                Operation.RECT -> {
                    val (_, width, height) = operation
                    for (y in 0 until height) for (x in 0 until width) screen[y][x] = '#'
                }
                Operation.ROTATE_COLUMN -> {
                    val (_, x, shift) = operation
                    val columnSize = screen.size
                    // Copy pixels from column
                    val pixels = Array(columnSize) { '.' }
                    for (y in screen.indices) pixels[y] = screen[y][x]
                    // Rotate column
                    for (y in screen.indices) screen[(y + shift) % columnSize][x] = pixels[y]
                }
                Operation.ROTATE_ROW -> {
                    val (_, y, shift) = operation
                    val rowSize = screen[0].size
                    // Copy pixels from row
                    val pixels = Array(rowSize) { '.' }
                    for (x in screen[0].indices) pixels[x] = screen[y][x]
                    // Rotate row
                    for (x in screen[0].indices) screen[y][(x + shift) % rowSize] = pixels[x]
                }
            }
        }

        private enum class Operation {
            RECT, ROTATE_COLUMN, ROTATE_ROW
        }

        private fun parse(instruction: String): Triple<Operation, Int, Int> {
            return when {
                instruction.startsWith("rect") -> {
                    val (width, height) = instruction.drop(5).split("x")
                    Triple(Operation.RECT, width.toInt(), height.toInt())
                }
                instruction.startsWith("rotate column") -> {
                    val (x, shift) = instruction.drop(16).split(" by ")
                    Triple(Operation.ROTATE_COLUMN, x.toInt(), shift.toInt())
                }
                instruction.startsWith("rotate row") -> {
                    val (y, shift) = instruction.drop(13).split(" by ")
                    Triple(Operation.ROTATE_ROW, y.toInt(), shift.toInt())
                }
                else -> {
                    error("Could not parse instruction: $instruction")
                }
            }
        }
    }
}
