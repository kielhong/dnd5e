package com.widehouse.dnd.character.player

import com.widehouse.dnd.challenge.RollResult
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
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.item.Armor
import com.widehouse.dnd.item.ArmorType
import com.widehouse.dnd.item.Coin
import com.widehouse.dnd.item.Item
import com.widehouse.dnd.item.Shield
import com.widehouse.dnd.item.Weapon
import com.widehouse.dnd.item.WeaponProperty.Finesse
import com.widehouse.dnd.item.WeaponProperty.Thrown
import com.widehouse.dnd.item.WeaponType
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
    val proficiencyBonus
        get() = (level - 1) / 4 + 2

    override val armorClass: Int
        get() = (equipment.armor?.armorClass ?: 0) + armorModifier() + (equipment.shield?.armorClass ?: 0)

    val attacks = Attacks()
    val equipment = Equipment()
    var coin: Coin = Coin(0)

    override fun attackRoll(target: Character, modifiers: List<Int>, dice: Dice): RollResult {
        return when (val roll = dice.roll()) {
            1 -> RollResult.CriticalFail
            20 -> RollResult.CriticalSuccess
            else -> if (roll + modifiers.sum() >= target.armorClass) RollResult.Success else RollResult.Fail
        }
    }

    override fun damageRoll(): Int {
        return attacks.mainWeapon().damageRoll()
    }

    override fun dead() = hitPoints <= 0

    override fun getDamage(damage: Int) {
        hitPoints = (hitPoints - damage).coerceAtLeast(0)
    }

    fun removeDamage(point: Int) {
        hitPoints = min(hitPoints + point, maxHitPoints)
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

    fun attackModifiers(): Int {
        val weapon = attacks.mainWeapon()
        return when (weapon.type) {
            WeaponType.Melee -> {
                if (weapon.properties.contains(Finesse) || weapon.properties.contains(Thrown)) {
                    abilities.dexterity.modifier
                } else {
                    abilities.strength.modifier
                }
            }
            WeaponType.Range -> abilities.dexterity.modifier
        }
    }

    // equipment, weapon, armor
    fun switchWeapon(weapon: Weapon) {
        attacks.weapons.clear()
        attacks.weapons.add(weapon)
    }

    fun getItem(item: Item) {
        equipment.items.add(item)
    }

    fun dropItem(item: Item) {
        equipment.items.remove(item)
    }

    private fun armorModifier() =
        when (equipment.armor?.itemType) {
            ArmorType.LightArmor -> abilities.dexterity.modifier
            ArmorType.MediumArmor -> abilities.dexterity.modifier.coerceAtMost(2)
            ArmorType.HeavyArmor -> 0
            else -> 0
        }

    // xp, level
    fun gainXp(xp: Int) {
        experiencePoints += xp
    }

    fun checkLevelUp(): Boolean {
        val nextLevelTable = LevelTable.of(level + 1)

        return experiencePoints >= nextLevelTable.xp
    }

    fun levelUp() {
        if (!checkLevelUp()) {
            return
        }

        level += 1
        maxHitPoints += `class`.hitDice.side + abilities.constitution.modifier
    }

    inner class Equipment {
        var armor: Armor? = null
        var shield: Shield? = null
        var items = mutableListOf<Item>()
    }

    inner class Attacks {
        val weapons = mutableListOf<Weapon>()
        fun mainWeapon() = weapons.first()
    }

    companion object {
        fun create(
            name: String,
            race: Race,
            `class`: Class,
            abilities: Abilities
        ): PlayerCharacter {
            return PlayerCharacter(
                name,
                race,
                `class`,
                abilities,
                1,
                0,
                `class`.proficiencySavingThrow,
                emptyList(),
                `class`.hitDice.side + abilities.constitution.modifier
            )
        }
    }
}
