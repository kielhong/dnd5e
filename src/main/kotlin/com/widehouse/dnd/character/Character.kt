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
    val characterClass: CharacterClass,
    val level: Int,
    val race: Race,
    abilities: Abilities = Abilities(0, 0, 0, 0, 0, 0),
    var maxHitPoints: Int
) : Creature(abilities) {
    init {
        hitPoints = maxHitPoints
    }
    var weapon: Weapon = Weapon("")
    var armor: Armor? = null
    var shield: Shield? = null
    override val armorClass: Int
        get() = (armor?.armorClass ?: 0) + armorModifier() + (shield?.armorClass ?: 0)
    var coin: Coin = Coin(0)
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
            else -> diceRoll + attackModifier(weapon) + proficiency() >= target.armorClass
        }
    }

    fun dealDamage(): Int {
        return weapon.damageRoll()
    }

    fun removeDamage(point: Int) {
        hitPoints = min(hitPoints + point, maxHitPoints)
    }

    fun proficiency() = (level - 1) / 4 + 2

    fun equip(item: Item) {
        when (item) {
            is Weapon -> weapon = item
            is Armor -> armor = item
            is Shield -> shield = item
        }
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

    private fun attackModifier(weapon: Weapon) =
        if (weapon.properties.contains(Finesse) || weapon.properties.contains(Thrown)) {
            dexterity.modifier
        } else {
            strength.modifier
        }

    private fun armorModifier() =
        when (armor?.itemType) {
            ArmorType.LightArmor -> dexterity.modifier
            ArmorType.MediumArmor -> dexterity.modifier.coerceAtMost(2)
            ArmorType.HeavyArmor -> 0
            else -> 0
        }

    fun plusCoin(coin: Coin) {
        this.coin += coin
    }

    fun minusCoin(coin: Coin) {
        this.coin -= coin
    }
}
