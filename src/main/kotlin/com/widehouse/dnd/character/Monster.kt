package com.widehouse.dnd.character

import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die
import com.widehouse.dnd.item.Weapon
import com.widehouse.dnd.item.WeaponProperty

class Monster(
    val name: String,
    val size: Size,
    val type: String,
    var hitPoints: Int,
    private val abilities: Abilities,
    override val armorClass: Int
    ) : Creature() {
    val strength = Strength(abilities.str)
    val dexterity = Dexterity(abilities.dex)
    val constitution = Constitution(abilities.con)
    val intelligence = Intelligence(abilities.int)
    val wisdom = Wisdom(abilities.wis)
    val charisma = Charisma(abilities.cha)

    val weapon: Weapon = Weapon("", listOf(), "")

    override fun attack(target: Creature): AttackResult {
        return AttackResult(target, if (attackRoll(target)) dealDamage() else 0)
    }

    override fun getDamage(point: Int) {
        hitPoints -= point
    }

    override fun dead() = hitPoints <= 0

    fun attackRoll(target: Creature): Boolean {
        return when (val diceRoll = Dice().roll(Die.D20)) {
            1 -> false
            20 -> true
            else -> diceRoll >= target.armorClass
        }
    }

    fun dealDamage(): Int {
        return weapon.damageRoll()
    }

}

enum class Size {
    Tiny,
    Small,
    Medium,
    Large,
    Huge,
    Gargantuan
}
