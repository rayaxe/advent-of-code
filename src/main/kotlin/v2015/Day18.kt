package v2015

class Day18 {
    private class Light(val state: Char, val nextState: Char)

    companion object {
        fun part1(input: List<String>, steps: Int = 100): Long {
            val grid = parse(input)
            repeat(steps) {
                animate(grid)
                update(grid)
            }
            return count(grid).toLong()
        }

        fun part2(input: List<String>, steps: Int = 100): Long {
            val grid = parse(input)
            setStuckCornerLights(grid)
            repeat(steps) {
                animate(grid)
                update(grid)
                setStuckCornerLights(grid)
            }
            return count(grid).toLong()
        }

        private fun setStuckCornerLights(grid: Array<Array<Light>>) {
            val maxY = grid.size - 1
            val maxX = grid[0].size - 1
            grid[0][0] = Light('#', '#')
            grid[0][maxX] = Light('#', '#')
            grid[maxY][0] = Light('#', '#')
            grid[maxY][maxX] = Light('#', '#')
        }

        private fun animate(grid: Array<Array<Light>>) {
            for (y in grid.indices) {
                for (x in grid[0].indices) {
                    var count = 0
                    for (dy in -1..1) {
                        for (dx in -1..1) {
                            if (dy == 0 && dx == 0) {
                                continue
                            }
                            val neighborY = y + dy
                            val neighborX = x + dx
                            if (neighborY in grid.indices && neighborX in grid.indices) {
                                if (grid[neighborY][neighborX].state == '#') {
                                    count++
                                }
                            }
                            val state = grid[y][x].state
                            val nextState = if (state == '#') {
                                if (count == 2 || count == 3) '#' else '.'
                            } else {
                                if (count == 3) '#' else '.'
                            }
                            grid[y][x] = Light(state, nextState)
                        }
                    }
                }
            }
        }

        private fun update(grid: Array<Array<Light>>) {
            for (y in grid.indices) {
                for (x in grid[0].indices) {
                    grid[y][x] = Light(grid[y][x].nextState, '.')
                }
            }
        }

        private fun count(grid: Array<Array<Light>>): Int {
            var count = 0
            for (y in grid.indices) {
                for (x in grid[0].indices) {
                    if (grid[y][x].state == '#') {
                        count++
                    }
                }
            }
            return count
        }

        private fun parse(input: List<String>): Array<Array<Light>> {
            val grid = Array(input.size) { Array(input[0].length) { Light('.', '.') } }
            input.forEachIndexed { y, row -> row.forEachIndexed { x, char -> grid[y][x] = Light(char, char) } }
            return grid
        }
    }
}
