package com.widehouse.dnd.character

import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die
import com.widehouse.dnd.item.Armor
import com.widehouse.dnd.item.ArmorType
import com.widehouse.dnd.item.Weapon
import java.lang.Math.max
import kotlin.math.min

class Character(
    val ability: Map<String, Ability>,
    val level: Int,
    var maxHitPoints: Int,
    val weapon: Weapon = Weapon("", listOf(), ""),
    val armor: Armor = Armor("", ArmorType.LightArmor, 0),
    private val dice: Dice = Dice()
) {
    var currentHitPoints = maxHitPoints

    fun attack(target: Character): AttackResult {
        return AttackResult(target, if (attackRoll(target)) dealDamage() else 0)
    }

    fun attackRoll(target: Character): Boolean {
        return when (val diceRoll = dice.roll(Die.D20)) {
            1 -> false
            20 -> true
            else -> diceRoll + ability["str"]!!.modifier() + proficiency() >= target.armorClass()
        }
    }

    fun dealDamage(): Int {
        return weapon.damageRoll()
    }

    fun getDamage(point: Int) {
        currentHitPoints = max(currentHitPoints - point, 0)
    }

    fun removeDamage(point: Int) {
        currentHitPoints = min(currentHitPoints + point, maxHitPoints)
    }

    fun armorClass(): Int {
        return armor.armorClass + when (armor.armorType) {
            ArmorType.LightArmor -> ability["dex"]!!.modifier()
            ArmorType.MediumArmor -> ability["dex"]!!.modifier().coerceAtMost(2)
            ArmorType.HeavyArmor -> 0
        }
    }

    fun proficiency(): Int {
        return (level - 1) / 4 + 2
    }

    fun hitPoints(): Int {
        return currentHitPoints
    }

    fun dead(): Boolean {
        return currentHitPoints <= 0
    }
}
