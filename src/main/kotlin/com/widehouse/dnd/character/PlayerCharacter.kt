package com.widehouse.dnd.character

import com.widehouse.dnd.challenge.Challenge
import com.widehouse.dnd.character.AbilityType.Charisma
import com.widehouse.dnd.character.AbilityType.Constitution
import com.widehouse.dnd.character.AbilityType.Dexterity
import com.widehouse.dnd.character.AbilityType.Intelligence
import com.widehouse.dnd.character.AbilityType.Strength
import com.widehouse.dnd.character.AbilityType.Wisdom
import com.widehouse.dnd.character.action.AttackResult
import com.widehouse.dnd.dice.Dice.D20
import com.widehouse.dnd.dice.RollCondition
import com.widehouse.dnd.item.Armor
import com.widehouse.dnd.item.ArmorType
import com.widehouse.dnd.item.Coin
import com.widehouse.dnd.item.Item
import com.widehouse.dnd.item.Shield
import com.widehouse.dnd.item.Weapon
import com.widehouse.dnd.item.WeaponProperty.Finesse
import com.widehouse.dnd.item.WeaponProperty.Thrown
import kotlin.math.min

class PlayerCharacter(
    val name: String,
    val `class`: Class,
    var level: Int,
    val race: Race,
    var experiencePoints: Int = 0,
    abilities: Abilities = Abilities(Strength(0), Dexterity(0), Constitution(0), Intelligence(0), Wisdom(0), Charisma(0)),
    val proficiencySavingThrow: List<AbilityType> = emptyList(),
    val proficiencySkill: List<Skill> = emptyList(),
    var maxHitPoints: Int
) : Creature(abilities) {
    init {
        hitPoints = maxHitPoints
    }
    val equipment = Equipment()

    override val armorClass: Int
        get() = (equipment.armor?.armorClass ?: 0) + armorModifier() + ((equipment.offHand as? Shield)?.armorClass ?: 0)
    val proficiencyBonus
        get() = (level - 1) / 4 + 2
    var coin: Coin = Coin(0)
    val inventory: MutableList<Item> = mutableListOf()

    override fun attack(target: Creature): AttackResult {
        return AttackResult(target, if (attackRoll(target)) dealDamage() else 0)
    }

    override fun dead() = hitPoints <= 0

    override fun getDamage(point: Int) {
        hitPoints = (hitPoints - point).coerceAtLeast(0)
    }

    fun attackRoll(target: Creature, condition: RollCondition? = null): Boolean {
        return when (val diceRoll = D20.roll()) {
            1 -> false
            20 -> true
            else -> Challenge.challenge(diceRoll, listOf(proficiencyBonus, attackModifier(equipment.mainHand as? Weapon)), target.armorClass)
        }
    }

    fun dealDamage(): Int {
        return (equipment.mainHand as? Weapon)?.damageRoll() ?: 0
    }

    fun removeDamage(point: Int) {
        hitPoints = min(hitPoints + point, maxHitPoints)
    }

    fun equip(item: Item) {
        when (item) {
            is Weapon -> {
                equipment.mainHand?.let { unequip(it) }
                equipment.mainHand = item
            }
            is Shield -> {
                equipment.offHand?.let { unequip(it) }
                equipment.offHand = item
            }
            is Armor -> {
                equipment.armor?.let { unequip(it) }
                equipment.armor = item
            }
            else -> {
                equipment.accessory.add(item)
            }
        }
        inventory.remove(item)
    }

    fun unequip(item: Item) {
        when (item) {
            is Weapon -> equipment.mainHand = null
            is Shield -> equipment.offHand = null
            is Armor -> equipment.armor = null
            else -> equipment.accessory.remove(item)
        }
        inventory.add(item)
    }

    fun abilityByType(type: AbilityType) =
        when (type) {
            is Strength -> strength
            is Dexterity -> dexterity
            is Constitution -> constitution
            is Intelligence -> intelligence
            is Wisdom -> wisdom
            is Charisma -> charisma
        }

    fun earnExperiencePoints(xp: Int) {
        experiencePoints += xp

        for (table in LevelTable.values()) {
            if (level < table.level && experiencePoints >= table.xp) {
                level = table.level
            }
        }
    }

    fun getItem(item: Item) {
        inventory.add(item)
    }

    fun dropItem(item: Item) {
        inventory.remove(item)
    }

    private fun attackModifier(weapon: Weapon?): Int {
        if (weapon == null) return 0

        return if (weapon.properties.contains(Finesse) || weapon.properties.contains(Thrown)) {
            dexterity.modifier
        } else {
            strength.modifier
        }
    }

    private fun armorModifier() =
        when (equipment.armor?.itemType) {
            ArmorType.LightArmor -> dexterity.modifier
            ArmorType.MediumArmor -> dexterity.modifier.coerceAtMost(2)
            ArmorType.HeavyArmor -> 0
            else -> 0
        }

    inner class Equipment {
        var mainHand: Item? = null
        var offHand: Item? = null
        var armor: Armor? = null
        var accessory: MutableList<Item> = mutableListOf()
    }

    companion object {
        fun create(
            name: String,
            race: Race,
            `class`: Class,
            abilities: Abilities
        ): PlayerCharacter {
            val hitPoint = `class`.hitDice.side + Constitution(abilities.constitution.score).modifier
            return PlayerCharacter(name, `class`, 1, race, 0, abilities, `class`.proficiencySavingThrow, emptyList(), hitPoint)
        }
    }
}
