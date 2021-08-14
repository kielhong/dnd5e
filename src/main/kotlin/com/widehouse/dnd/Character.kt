package com.widehouse.dnd

class Character(
    private val ability: Ability,
    val proficiency: Int,
    val weapon: Weapon = Weapon("", listOf(), ""),
    private val dice: Dice = Dice()
) {
    fun attack(target: Character): AttackResult {
        return AttackResult(if (attackRoll(target)) damage() else 0)
    }

    fun modifier(): Int {
        return (ability.str - 10) / 2
    }

    fun attackRoll(target: Character): Boolean {
        return when(val diceRoll = dice.roll(20)) {
            1 -> false
            20 -> true
            else -> diceRoll + modifier() + proficiency >= target.getArmorClass()
        }
    }

    fun damage(): Int {
        return weapon.damageRoll()
    }

    fun getArmorClass(): Int {
        return 16
    }
}