package v2015

import kotlin.math.max
import kotlin.math.min

class Day22 {

    private abstract class Actor(var hitPoints: Int, var armor: Int) {
        fun hit(damage: Int) {
            this.hitPoints -= max(damage - this.armor, 1)
        }

        fun addHitPoints(hitPoints: Int) {
            this.hitPoints += hitPoints
        }

        fun addArmor(armor: Int) {
            this.armor += armor
        }

        abstract fun copy(): Actor
    }

    private class Boss(hitPoints: Int, var damage: Int, armor: Int = 0) : Actor(hitPoints, armor) {
        override fun copy(): Boss {
            return Boss(this.hitPoints, this.damage, this.armor)
        }
    }

    private class Wizard(
        hitPoints: Int,
        var mana: Int,
        armor: Int = 0,
        val activeSpells: MutableList<Spell> = mutableListOf()
    ) : Actor(hitPoints, armor) {
        fun addMana(mana: Int) {
            this.mana += mana
        }

        fun cast(spell: Spell) {
            this.mana -= spell.cost
            this.activeSpells.add(spell)
        }

        fun applySpellEffects(boss: Boss) {
            this.activeSpells.map {
                if (it.turnsLeft == it.duration) {
                    it.startEffect(this, boss)
                }
                it.repeatingEffect(this, boss)
                it.turnsLeft -= 1
                if (it.turnsLeft == 0) {
                    it.endEffect(this, boss)
                }
            }
            this.activeSpells.removeAll { it.turnsLeft == 0 }
        }

        override fun copy(): Wizard {
            return Wizard(
                this.hitPoints,
                this.mana,
                this.armor,
                this.activeSpells.map { it.copy() }.toMutableList()
            )
        }
    }

    private class Spell(
        val name: String,
        val cost: Int,
        var duration: Int,
        val repeatingEffect: (Wizard, Boss) -> Unit,
        val startEffect: (Wizard, Boss) -> Unit = { _: Wizard, _: Boss -> },
        val endEffect: (Wizard, Boss) -> Unit = { _: Wizard, _: Boss -> }
    ) {
        var turnsLeft = duration
        fun copy(): Spell {
            return Spell(
                this.name,
                this.cost,
                this.duration,
                this.repeatingEffect,
                this.startEffect,
                this.endEffect
            ).also {
                it.turnsLeft = this.turnsLeft
            }
        }
    }

    enum class Difficulty { EASY, HARD }

    companion object {
        fun part1(input: List<String>, wizardHitPoints: Int = 50, wizardMana: Int = 500): Long {
            val (bossHitPoints, bossDamage) = input.map { it.split(": ").last().toInt() }
            return play(wizardHitPoints, wizardMana, bossHitPoints, bossDamage, Difficulty.EASY)
        }

        fun part2(input: List<String>, wizardHitPoints: Int = 50, wizardMana: Int = 500): Long {
            val (bossHitPoints, bossDamage) = input.map { it.split(": ").last().toInt() }
            return play(wizardHitPoints, wizardMana, bossHitPoints, bossDamage, Difficulty.HARD)
        }

        fun play(
            wizardHitPoints: Int,
            wizardMana: Int,
            bossHitPoints: Int,
            bossDamage: Int,
            difficulty: Difficulty
        ): Long {
            var leastAmountOfManaSpent = Int.MAX_VALUE
            fun play(wizardTemplate: Wizard, bossTemplate: Boss, manaSpent: Int, turn: Int) {
                for (spell in SPELLS) {
                    if (wizardTemplate.activeSpells.any { it.name == spell.name && it.turnsLeft > 1 } || spell.cost > wizardTemplate.mana) {
                        continue
                    }
                    val wizard = wizardTemplate.copy()
                    val boss = bossTemplate.copy()

                    // Wizard turn
                    if (difficulty == Difficulty.HARD) {
                        wizard.addHitPoints(-1)
                        if (wizard.hitPoints <= 0) {
                            continue
                        }
                    }
                    wizard.applySpellEffects(boss)
                    wizard.cast(spell.copy())

                    // Boss turn
                    wizard.applySpellEffects(boss)
                    wizard.hit(boss.damage)

                    // Finished or next turn?
                    val newManaSpent = manaSpent + spell.cost
                    if (boss.hitPoints <= 0) {
                        leastAmountOfManaSpent = min(leastAmountOfManaSpent, newManaSpent)
                    } else if (wizard.hitPoints > 0 && newManaSpent < leastAmountOfManaSpent) {
                        play(wizard, boss, newManaSpent, turn + 1)
                    }
                }
            }
            play(Wizard(wizardHitPoints, wizardMana), Boss(bossHitPoints, bossDamage), 0, 0)
            return leastAmountOfManaSpent.toLong()
        }

        private val SPELLS = listOf(
            Spell("Magic Missile", 53, 1, { _: Wizard, boss: Boss -> boss.hit(4) }),
            Spell("Drain", 73, 1, { wizard: Wizard, boss: Boss -> boss.hit(2); wizard.addHitPoints(2) }),
            Spell(
                "Shield", 113, 6,
                { _: Wizard, _: Boss -> },
                { wizard: Wizard, _: Boss -> wizard.addArmor(7) },
                { wizard: Wizard, _: Boss -> wizard.addArmor(-7) },
            ),
            Spell("Poison", 173, 6, { _: Wizard, boss: Boss -> boss.hit(3) }),
            Spell("Recharge", 229, 5, { wizard: Wizard, _: Boss -> wizard.addMana(101) })
        )
    }
}
