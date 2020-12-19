private data class CubeState(val current: Boolean, val next: Boolean)

fun day17Part1(input: List<String>): Long {
    val numberOfCycles = 6
    val depth = 1
    val height = input.size
    val width = input[0].length

    // Init dimension
    val dimension = Array(depth + 2 * numberOfCycles) {
        Array(height + 2 * numberOfCycles) {
            Array(width + 2 * numberOfCycles) {
                CubeState(false, false)
            }
        }
    }

    // Init states
    for (z in 0 until depth) {
        for (y in 0 until height) {
            for (x in 0 until width) {
                dimension[z + numberOfCycles][y + numberOfCycles][x + numberOfCycles] = if (input[y][x] == '#') {
                    CubeState(true, false)
                } else {
                    CubeState(false, false)
                }
            }
        }
    }

    // Simulate
    repeat(numberOfCycles) {

        // Apply rules
        for (z in 0 until depth + 2 * numberOfCycles) {
            for (y in 0 until height + 2 * numberOfCycles) {
                for (x in 0 until width + 2 * numberOfCycles) {
                    var activeNeighbors = 0
                    for (z1 in -1..1) {
                        for (y1 in -1..1) {
                            for (x1 in -1..1) {
                                if (z1 == 0 && y1 == 0 && x1 == 0) {
                                    continue
                                }
                                val neighborZ = z + z1
                                val neighborY = y + y1
                                val neighborX = x + x1
                                if (!(neighborZ in dimension.indices && neighborY in dimension[0].indices && neighborX in dimension[0][0].indices)) {
                                    continue
                                }
                                if (dimension[neighborZ][neighborY][neighborX].current) {
                                    activeNeighbors++
                                }
                            }
                        }
                    }
                    val state = dimension[z][y][x].current
                    val nextState = (state && activeNeighbors in 2..3) || (!state && activeNeighbors == 3)
                    dimension[z][y][x] = CubeState(state, nextState)
                }
            }
        }

        // Apply changes
        for (z in 0 until depth + 2 * numberOfCycles) {
            for (y in 0 until height + 2 * numberOfCycles) {
                for (x in 0 until width + 2 * numberOfCycles) {
                    val current = dimension[z][y][x]
                    dimension[z][y][x] = CubeState(current.next, false)
                }
            }
        }
    }

    // Count active cubes
    var count = 0L
    for (z in 0 until depth + 2 * numberOfCycles) {
        for (y in 0 until height + 2 * numberOfCycles) {
            for (x in 0 until width + 2 * numberOfCycles) {
                if (dimension[z][y][x].current) {
                    count++
                }
            }
        }
    }
    return count
}

fun day17Part2(input: List<String>): Long {
    val numberOfCycles = 6
    val hyper = 1
    val depth = 1
    val height = input.size
    val width = input[0].length

    // Init dimension
    val dimension = Array(hyper + 2 * numberOfCycles) {
        Array(depth + 2 * numberOfCycles) {
            Array(height + 2 * numberOfCycles) {
                Array(width + 2 * numberOfCycles) {
                    CubeState(false, false)
                }
            }
        }
    }

    // Init states
    for (w in 0 until hyper) {
        for (z in 0 until depth) {
            for (y in 0 until height) {
                for (x in 0 until width) {
                    dimension[w + numberOfCycles][z + numberOfCycles][y + numberOfCycles][x + numberOfCycles] =
                        if (input[y][x] == '#') {
                            CubeState(true, false)
                        } else {
                            CubeState(false, false)
                        }
                }
            }
        }
    }

    // Simulate
    repeat(numberOfCycles) {

        // Apply rules
        for (w in 0 until depth + 2 * numberOfCycles) {
            for (z in 0 until depth + 2 * numberOfCycles) {
                for (y in 0 until height + 2 * numberOfCycles) {
                    for (x in 0 until width + 2 * numberOfCycles) {
                        var activeNeighbors = 0
                        for (w1 in -1..1) {
                            for (z1 in -1..1) {
                                for (y1 in -1..1) {
                                    for (x1 in -1..1) {
                                        if (w1 == 0 && z1 == 0 && y1 == 0 && x1 == 0) {
                                            continue
                                        }
                                        val neighborW = w + w1
                                        val neighborZ = z + z1
                                        val neighborY = y + y1
                                        val neighborX = x + x1
                                        if (!(neighborW in dimension.indices && neighborZ in dimension[0].indices && neighborY in dimension[0][0].indices && neighborX in dimension[0][0][0].indices)) {
                                            continue
                                        }
                                        if (dimension[neighborW][neighborZ][neighborY][neighborX].current) {
                                            activeNeighbors++
                                        }
                                    }
                                }
                            }
                            val state = dimension[w][z][y][x].current
                            val nextState = (state && activeNeighbors in 2..3) || (!state && activeNeighbors == 3)
                            dimension[w][z][y][x] = CubeState(state, nextState)
                        }
                    }
                }
            }
        }

        // Apply changes
        for (w in 0 until depth + 2 * numberOfCycles) {
            for (z in 0 until depth + 2 * numberOfCycles) {
                for (y in 0 until height + 2 * numberOfCycles) {
                    for (x in 0 until width + 2 * numberOfCycles) {
                        val current = dimension[w][z][y][x]
                        dimension[w][z][y][x] = CubeState(current.next, false)
                    }
                }
            }
        }
    }

    // Count active cubes
    var count = 0L
    for (w in 0 until depth + 2 * numberOfCycles) {
        for (z in 0 until depth + 2 * numberOfCycles) {
            for (y in 0 until height + 2 * numberOfCycles) {
                for (x in 0 until width + 2 * numberOfCycles) {
                    if (dimension[w][z][y][x].current) {
                        count++
                    }
                }
            }
        }
    }
    return count
}
