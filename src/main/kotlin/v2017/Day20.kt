package v2017

import kotlin.math.abs

class Day20 {
    private data class Particle(val p: Vector, val v: Vector, val a: Vector, var isDestroyed: Boolean = false) {
        fun update() {
            v add a
            p add v
        }

        fun handleCollision(other: Particle) {
            if (p.x == other.p.x && p.y == other.p.y && p.z == other.p.z) {
                isDestroyed = true
                other.isDestroyed = true
            }
        }
    }

    private data class Vector(var x: Int, var y: Int, var z: Int) {
        infix fun add(other: Vector) {
            x += other.x
            y += other.y
            z += other.z
        }
    }

    companion object {
        private const val NUMBER_OF_TICKS = 40

        fun part1(input: List<String>): Int {
            return input.map { parseParticle(it).a }
                .mapIndexed { index, vector -> index to with(vector) { listOf(x, y, z).map { abs(it) } } }
                .sortedWith(compareBy({ it.second.maxOrNull()!! }, { it.second.sum() }))
                .first().first
        }

        fun part2(input: List<String>): Long {
            val particles = input.map { parseParticle(it) }
            for (tick in 0..NUMBER_OF_TICKS) {
                val activeParticles = particles.filter { !it.isDestroyed }
                activeParticles.forEach { it.update() }
                activeParticles.forEachIndexed { index, particle ->
                    for (otherIndex in index + 1 until activeParticles.size) {
                        particle.handleCollision(activeParticles[otherIndex])
                    }
                }
            }
            return particles.count { !it.isDestroyed }.toLong()
        }

        private fun parseParticle(text: String): Particle {
            return text.split(", ")
                .map { parseVector(it) }
                .let { (p, v, a) -> Particle(p, v, a) }
        }

        private fun parseVector(text: String): Vector {
            return text
                .drop(3).dropLast(1).split(",")
                .map { it.toInt() }
                .let { (x, y, z) -> Vector(x, y, z) }
        }
    }
}
