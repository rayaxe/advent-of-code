package v2016

class Day10 {
    companion object {
        fun part1(input: List<String>, higherValue: Long = 61L, lowerValue: Long = 17L): Long {
            val (bots, outputs) = parse(input)
            simulate(bots, outputs)
            return bots.filterValues { it.microchips.containsAll(listOf(higherValue, lowerValue)) }
                .map { it.key }
                .first()
        }

        fun part2(input: List<String>): Long {
            val (bots, outputs) = parse(input)
            simulate(bots, outputs)
            return outputs.getValue(0) * outputs.getValue(1) * outputs.getValue(2)
        }

        private fun simulate(bots: Map<Long, Bot>, outputs: MutableMap<Long, Long>) {
            fun give(bot: Bot) {
                val (lowIsBot, low) = bot.lowTarget
                val value = bot.low()
                if (lowIsBot) {
                    val lowBot = bots.getValue(low)
                    lowBot.add(value)
                    if (lowBot.isOn()) {
                        give(lowBot)
                    }
                } else {
                    outputs[low] = value
                }
                val (highIsBot, high) = bot.highTarget
                if (highIsBot) {
                    val highBot = bots.getValue(high)
                    highBot.add(bot.high())
                    if (highBot.isOn()) {
                        give(highBot)
                    }
                } else {
                    outputs[high] = bot.high()
                }
            }
            give(bots.values.first { it.isOn() })
        }

        private fun parse(input: List<String>): Pair<Map<Long, Bot>, MutableMap<Long, Long>> {
            val bots = mutableMapOf<Long, Bot>()
            val outputs = mutableMapOf<Long, Long>()
            val initialValues = mutableListOf<Pair<Long, Long>>()
            input.forEach { instruction ->
                when {
                    instruction.startsWith("bot") -> {
                        val (source, lowTarget, highTarget) = parse(instruction)
                        bots[source] = Bot(lowTarget, highTarget)
                    }
                    instruction.startsWith("value") -> {
                        val (value, bot) = instruction.drop(6).split(" goes to bot ").map { it.toLong() }
                        initialValues.add(value to bot)
                    }
                    else -> error("Could not parse instruction: $instruction")
                }
            }
            initialValues.forEach { (value, bot) -> bots.getValue(bot).add(value) }
            return Pair(bots.toMap(), outputs)
        }

        private val instructionRegex =
            "bot ([0-9]+) gives low to (bot|output) ([0-9]+) and high to (bot|output) ([0-9]+)".toRegex()

        private fun parse(text: String): Triple<Long, Pair<Boolean, Long>, Pair<Boolean, Long>> {
            return instructionRegex.matchEntire(text)
                ?.destructured
                ?.let { (source, lowTargetType, lowTarget, highTargetType, highTarget) ->
                    return Triple(
                        source.toLong(),
                        Pair(lowTargetType == "bot", lowTarget.toLong()),
                        Pair(highTargetType == "bot", highTarget.toLong())
                    )
                }
                ?: throw IllegalArgumentException("Bad input '$text'")
        }
    }

    private data class Bot(
        val lowTarget: Pair<Boolean, Long>,
        val highTarget: Pair<Boolean, Long>,
        val microchips: MutableList<Long> = mutableListOf()
    ) {
        fun add(value: Long) {
            microchips.add(value)
        }

        fun low(): Long {
            if (isOff()) error("Bot is not operational")
            return microchips.minOrNull()!!
        }

        fun high(): Long {
            if (isOff()) error("Bot is not operational")
            return microchips.maxOrNull()!!
        }

        fun isOn(): Boolean {
            return microchips.size == 2
        }

        fun isOff(): Boolean {
            return isOn().not()
        }
    }
}
