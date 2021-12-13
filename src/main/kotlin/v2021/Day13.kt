package v2021

class Day13 {
    companion object {
        fun part1(input: List<String>): Long {
            val (coordinates, instructions) = parse(input)
            val resultingPaper = foldPaper(createPaperFrom(coordinates), instructions.first())
            return resultingPaper.sumOf { row -> row.count { it == '#' } }.toLong()
        }

        fun part2(input: List<String>): Long {
            val (coordinates, instructions) = parse(input)
            val resultingPaper = instructions.fold(createPaperFrom(coordinates)) { paper, instruction ->
                foldPaper(paper, instruction)
            }
            printPaper(resultingPaper)
            return -1L
        }

        private fun printPaper(paper: Array<Array<Char>>) {
            paper.indices.forEach { y ->
                paper[0].indices.forEach { x ->
                    print(paper[y][x])
                }
                println()
            }
        }

        private fun foldPaper(paper: Array<Array<Char>>, instruction: Pair<String, Int>): Array<Array<Char>> {
            val (axis, pos) = instruction
            return when (axis) {
                "x" -> foldLeft(paper, pos)
                "y" -> foldUp(paper, pos)
                else -> error("Unsupported axis: $axis")
            }
        }

        private fun foldUp(paper: Array<Array<Char>>, pos: Int): Array<Array<Char>> {
            val height = paper.size
            val width = paper[0].size
            val newHeight = paper.size / 2
            if (newHeight != pos) {
                error("Unexpected fold pos: $pos")
            }
            val newPaper = Array(newHeight) { Array(width) { '.' } }
            (0 until newHeight).forEach { y ->
                (0 until width).forEach { x ->
                    if (paper[y][x] == '#') {
                        newPaper[y][x] = paper[y][x]
                    }
                    if (paper[height - y - 1][x] == '#') {
                        newPaper[y][x] = paper[height - y - 1][x]
                    }
                }
            }
            return newPaper
        }

        private fun foldLeft(paper: Array<Array<Char>>, pos: Int): Array<Array<Char>> {
            val height = paper.size
            val width = paper[0].size
            val newWidth = paper[0].size / 2
            if (newWidth != pos) {
                error("Unexpected fold pos: $pos")
            }
            val newPaper = Array(height) { Array(newWidth) { '.' } }
            (0 until height).forEach { y ->
                (0 until newWidth).forEach { x ->
                    if (paper[y][x] == '#') {
                        newPaper[y][x] = paper[y][x]
                    }
                    if (paper[y][width - x - 1] == '#') {
                        newPaper[y][x] = paper[y][width - x - 1]
                    }
                }
            }
            return newPaper
        }

        private fun createPaperFrom(coordinates: List<Pair<Int, Int>>): Array<Array<Char>> {
            val width = coordinates.maxOf { it.first } + 1
            val height = coordinates.maxOf { it.second } + 1
            val paper = Array(height) { Array(width) { '.' } }
            coordinates.forEach { (x, y) -> paper[y][x] = '#' }
            return paper
        }

        private fun parse(input: List<String>): Pair<List<Pair<Int, Int>>, List<Pair<String, Int>>> {
            val coordinates = input
                .takeWhile { line -> line != "" }
                .map { it.split(',').let { (x, y) -> x.toInt() to y.toInt() } }
            val instructions = input
                .takeLastWhile { line -> line != "" }
                .map { it.removePrefix("fold along ").split('=').let { (axis, pos) -> axis to pos.toInt() } }
            return coordinates to instructions
        }
    }
}
