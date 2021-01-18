package v2015

class Day25 {
    companion object {
        fun part1(inputRow: Int = 3010, inputCol: Int = 3019): Long {
            var code = 20151125L
            var row = 1
            var col = 1
            var steps = 1
            while (!(row == inputRow && col == inputCol)) {
                col++
                if (col > steps) {
                    col = 1
                    steps++
                }
                row = steps - col + 1
                code = (code * 252533) % 33554393
            }
            return code
        }
    }
}

