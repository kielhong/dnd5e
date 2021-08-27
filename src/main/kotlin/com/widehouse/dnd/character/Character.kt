package com.widehouse.dnd.character

import com.widehouse.dnd.character.AbilityType.Charisma
import com.widehouse.dnd.character.AbilityType.Constitution
import com.widehouse.dnd.character.AbilityType.Dexterity
import com.widehouse.dnd.character.AbilityType.Intelligence
import com.widehouse.dnd.character.AbilityType.Strength
import com.widehouse.dnd.character.AbilityType.Wisdom
import com.widehouse.dnd.character.action.AttackResult
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die.D20
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

class Character(
    val name: String,
    val `class`: Class,
    var level: Int,
    val race: Race,
    var experiencePoints: Int = 0,
    abilities: Abilities = Abilities(0, 0, 0, 0, 0, 0),
    val proficiencySavingThrow: List<AbilityType> = emptyList(),
    val proficiencySkill: List<Skill> = emptyList(),
    var maxHitPoints: Int,
) : Creature(abilities) {
    init {
        hitPoints = maxHitPoints
    }
    var weapon: Weapon? = null
    var armor: Armor? = null
    var shield: Shield? = null

    override val armorClass: Int
        get() = (armor?.armorClass ?: 0) + armorModifier() + (shield?.armorClass ?: 0)
    val proficiencyBonus
        get() = (level - 1) / 4 + 2
    var coin: Coin = Coin(0)
    val inventory: MutableList<Item> = mutableListOf()
    private val dice = Dice()

    override fun attack(target: Creature): AttackResult {
        return AttackResult(target, if (attackRoll(target)) dealDamage() else 0)
    }

    override fun dead() = hitPoints <= 0

    override fun getDamage(point: Int) {
        hitPoints = (hitPoints - point).coerceAtLeast(0)
    }

    fun attackRoll(target: Creature, condition: RollCondition? = null): Boolean {
        return when (val diceRoll = dice.roll(D20, condition)) {
            1 -> false
            20 -> true
            else -> diceRoll + attackModifier(weapon) + proficiencyBonus >= target.armorClass
        }
    }

    fun dealDamage(): Int {
        return weapon?.damageRoll() ?: 0
    }

    fun removeDamage(point: Int) {
        hitPoints = min(hitPoints + point, maxHitPoints)
    }

    fun equip(item: Item) {
        when (item) {
            is Weapon -> {
                weapon?.let { unequip(it) }
                weapon = item
            }
            is Armor -> {
                armor?.let { unequip(it) }
                armor = item
            }
            is Shield -> {
                shield?.let { unequip(it) }
                shield = item
            }
        }
        inventory.remove(item)
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
        when (armor?.itemType) {
            ArmorType.LightArmor -> dexterity.modifier
            ArmorType.MediumArmor -> dexterity.modifier.coerceAtMost(2)
            ArmorType.HeavyArmor -> 0
            else -> 0
        }

    private fun unequip(item: Item) {
        when (item) {
            is Weapon -> weapon = null
            is Armor -> armor = null
            is Shield -> shield = null
        }
        inventory.add(item)
    }
}
