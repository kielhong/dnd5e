package com.widehouse.dnd.character

import com.widehouse.dnd.character.action.Action
import com.widehouse.dnd.character.action.AttackResult
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die
import com.widehouse.dnd.item.Weapon

class Monster(
    val name: String,
    val size: Size,
    val type: String,
    abilities: Abilities,
    override var hitPoints: Int,
    override val armorClass: Int,
    private var action: Action = Action(0, Weapon(""))
) : Creature(abilities) {
    val dice = Dice()

    override fun attack(target: Creature): AttackResult {
        return AttackResult(target, if (attackRoll(target)) dealDamage() else 0)
    }

    override fun getDamage(point: Int) {
        hitPoints -= point
    }

    override fun dead() = hitPoints <= 0

    fun attackRoll(target: Creature): Boolean {
        return when (val diceRoll = dice.roll(Die.D20)) {
            1 -> false
            20 -> true
            else -> diceRoll + action.hitBonus >= target.armorClass
        }
    }

    fun dealDamage(): Int {
        return action.weapon.damageRoll()
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
