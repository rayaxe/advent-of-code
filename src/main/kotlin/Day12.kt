import kotlin.math.abs

fun day12Part1(input: List<String>): Long {
    return navigate(input, RegularShip())
}

fun day12Part2(input: List<String>): Long {
    return navigate(input, WaypointShip())
}

private fun navigate(input: List<String>, ship: Ship): Long {
    input.forEach {
        val instruction = it[0]
        val units = it.substring(1).toLong()
        ship.update(instruction, units)
    }
    return abs(ship.x) + abs(ship.y)
}

private abstract class Ship(var x: Long = 0, var y: Long = 0) {
    abstract fun moveDirection(direction: Direction, units: Long)
    abstract fun moveForward(units: Long)
    abstract fun turnRight(degrees: Int)

    fun update(instruction: Char, units: Long) {
        when (instruction) {
            'N' -> moveDirection(Direction.NORTH, units)
            'S' -> moveDirection(Direction.SOUTH, units)
            'E' -> moveDirection(Direction.EAST, units)
            'W' -> moveDirection(Direction.WEST, units)
            'L' -> turnLeft(units.toInt())
            'R' -> turnRight(units.toInt())
            'F' -> moveForward(units)
            else -> throw IllegalArgumentException("Unexpected instruction: $instruction")
        }
    }

    private fun turnLeft(degrees: Int) {
        turnRight(360 - degrees)
    }
}

private data class RegularShip(var direction: Direction = Direction.EAST) : Ship() {
    override fun moveDirection(direction: Direction, units: Long) {
        x += direction.x * units
        y += direction.y * units
    }

    override fun moveForward(units: Long) {
        moveDirection(direction, units)
    }

    override fun turnRight(degrees: Int) {
        val ordinalSteps = degrees / 90
        direction = Direction.values()[(direction.ordinal + ordinalSteps) % 4]
    }
}

private data class WaypointShip(var waypoint: Pair<Long, Long> = Pair(10, 1)) : Ship() {
    override fun moveDirection(direction: Direction, units: Long) {
        waypoint = with(waypoint) {
            Pair(first + direction.x * units, second + direction.y * units)
        }
    }

    override fun moveForward(units: Long) {
        x += waypoint.first * units
        y += waypoint.second * units
    }

    override fun turnRight(degrees: Int) {
        waypoint = with(waypoint) {
            when (degrees) {
                90 -> Pair(second, first * -1)
                180 -> Pair(first * -1, second * -1)
                270 -> Pair(second * -1, first)
                else -> throw IllegalArgumentException("Unexpected degrees: $degrees")
            }
        }
    }
}

private enum class Direction(val x: Int, val y: Int) {
    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0)
}
