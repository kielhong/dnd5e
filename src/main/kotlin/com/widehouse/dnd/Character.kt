package com.widehouse.dnd

import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die

class Character(
    val ability: Map<String, Ability>,
    val proficiency: Int,
    val weapon: Weapon = Weapon("", listOf(), ""),
    val armor: Armor = Armor("", "", 0),
    private val dice: Dice = Dice()
) {
    fun attack(target: Character): AttackResult {
        return AttackResult(if (attackRoll(target)) damage() else 0)
    }

    fun attackRoll(target: Character): Boolean {
        return when (val diceRoll = dice.roll(Die.D20)) {
            1 -> false
            20 -> true
            else -> diceRoll + ability["str"]!!.modifier() + proficiency >= target.armorClass()
        }
    }

    fun damage(): Int {
        return weapon.damageRoll()
    }

    fun armorClass(): Int {
        return armor.ac + when (armor.category) {
            "Light Armor" -> ability["dex"]!!.modifier()
            "Medium Armor" -> ability["dex"]!!.modifier().coerceAtMost(2)
            else -> 0
        }
    }
}
