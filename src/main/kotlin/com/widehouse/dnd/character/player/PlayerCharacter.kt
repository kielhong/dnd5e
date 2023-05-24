package com.widehouse.dnd.character.player

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.Skill
import com.widehouse.dnd.character.ability.Abilities
import com.widehouse.dnd.character.ability.AbilityType
import com.widehouse.dnd.character.ability.AbilityType.Charisma
import com.widehouse.dnd.character.ability.AbilityType.Constitution
import com.widehouse.dnd.character.ability.AbilityType.Dexterity
import com.widehouse.dnd.character.ability.AbilityType.Intelligence
import com.widehouse.dnd.character.ability.AbilityType.Strength
import com.widehouse.dnd.character.ability.AbilityType.Wisdom
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
    override val name: String,
    val race: Race,
    val `class`: Class,
    override var abilities: Abilities = Abilities.of(10, 10, 10, 10, 10, 10),
    var level: Int,
    var experiencePoints: Int = 0,
    val proficiencySavingThrow: List<AbilityType> = emptyList(),
    val proficiencySkill: List<Skill> = emptyList(),
    var maxHitPoints: Int
) : Character(name, abilities, maxHitPoints) {
    val equipment = Equipment()

    override fun armorClass(): Int {
        return (equipment.armor?.armorClass ?: 0) + armorModifier() + ((equipment.offHand as? Shield)?.armorClass ?: 0)
    }
    val proficiencyBonus
        get() = (level - 1) / 4 + 2
    var coin: Coin = Coin(0)
    val inventory: MutableList<Item> = mutableListOf()

//    fun attack(target: Creature): AttackResult {
//        return AttackResult(target, if (attackRoll(target)) dealDamage() else 0)
//    }

    fun dead() = hitPoints <= 0

    fun getDamage(point: Int) {
        hitPoints = (hitPoints - point).coerceAtLeast(0)
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
            is Strength -> abilities.strength
            is Dexterity -> abilities.dexterity
            is Constitution -> abilities.constitution
            is Intelligence -> abilities.intelligence
            is Wisdom -> abilities.wisdom
            is Charisma -> abilities.charisma
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
            abilities.dexterity.modifier
        } else {
            abilities.strength.modifier
        }
    }

    private fun armorModifier() =
        when (equipment.armor?.itemType) {
            ArmorType.LightArmor -> abilities.dexterity.modifier
            ArmorType.MediumArmor -> abilities.dexterity.modifier.coerceAtMost(2)
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
            val hitPoint = `class`.hitDice.side + com.widehouse.dnd.character.ability.Constitution(abilities.constitution.score).modifier
            return PlayerCharacter(
                name,
                race,
                `class`,
                abilities,
                1,
                0,
                `class`.proficiencySavingThrow,
                emptyList(),
                hitPoint
            )
        }
    }
}
