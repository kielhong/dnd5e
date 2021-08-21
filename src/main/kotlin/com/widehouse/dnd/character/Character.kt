package com.widehouse.dnd.character

import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die
import com.widehouse.dnd.item.Armor
import com.widehouse.dnd.item.ArmorType
import com.widehouse.dnd.item.Item
import com.widehouse.dnd.item.Shield
import com.widehouse.dnd.item.Weapon
import com.widehouse.dnd.item.WeaponProperty.Finesse
import com.widehouse.dnd.item.WeaponProperty.Thrown
import java.lang.Math.max
import kotlin.math.min

class Character(
    val name: String,
    val characterClass: CharacterClass,
    val level: Int,
    val race: Race,
    val abilities: Abilities = Abilities(0, 0, 0, 0, 0, 0),
    var maxHitPoints: Int,
    private val dice: Dice = Dice()
) {
    private var currentHitPoints = maxHitPoints
    var strength = Strength(abilities.str)
    var dexterity = Dexterity(abilities.dex)
    var constitution = Constitution(abilities.con)
    var intelligence = Intelligence(abilities.int)
    var wisdom = Wisdom(abilities.wis)
    var charisma = Charisma(abilities.cha)

    var weapon: Weapon = Weapon("", listOf(), "")
    var armor: Armor? = null
    var shield: Shield? = null

    fun attack(target: Character): AttackResult {
        return AttackResult(target, if (attackRoll(target)) dealDamage() else 0)
    }

    fun attackRoll(target: Character): Boolean {
        return when (val diceRoll = dice.roll(Die.D20)) {
            1 -> false
            20 -> true
            else -> diceRoll + attackModifier(weapon) + proficiency() >= target.armorClass()
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
        return (armor?.armorClass ?: 0) + armorModifier() + (shield?.armorClass ?: 0)
    }

    fun proficiency() = (level - 1) / 4 + 2

    fun hitPoints() = currentHitPoints

    fun maxHitPoints() = maxHitPoints

    fun dead(): Boolean {
        return currentHitPoints <= 0
    }

    fun equip(item: Item) {
        when (item) {
            is Weapon -> weapon = item
            is Armor -> armor = item
            is Shield -> shield = item
        }
    }

    private fun attackModifier(weapon: Weapon): Int {
        return if (weapon.properties.contains(Finesse) || weapon.properties.contains(Thrown)) {
            dexterity.modifier()
        } else {
            strength.modifier()
        }
    }

    private fun armorModifier(): Int {
        return when (armor?.itemType) {
            ArmorType.LightArmor -> dexterity.modifier()
            ArmorType.MediumArmor -> dexterity.modifier().coerceAtMost(2)
            ArmorType.HeavyArmor -> 0
            else -> 0
        }
    }
}
