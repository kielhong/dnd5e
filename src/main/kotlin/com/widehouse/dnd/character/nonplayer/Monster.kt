package com.widehouse.dnd.character.nonplayer

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.ability.Abilities
import com.widehouse.dnd.character.action.Action
import com.widehouse.dnd.character.item.Weapon
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.RollResult

class Monster(
    override val name: String,
    override var abilities: Abilities,
    override var hitPoints: Int,
    val size: MonsterSize,
    val type: String,
    override val armorClass: Int,
    private var action: Action = Action(0, Weapon(""))
) : Character(name, abilities, hitPoints) {
    override fun attackRoll(target: Character, modifiers: List<Int>, dice: Dice): RollResult {
        return when (val roll = dice.roll()) {
            1 -> RollResult.CriticalFail
            20 -> RollResult.CriticalSuccess
            else -> if (roll + modifiers.sum() >= target.armorClass) RollResult.Success else RollResult.Fail
        }
    }

    override fun damageRoll(): Int {
        return action.weapon.damageRoll()
    }

    override fun getDamage(damage: Int) {
        TODO("Not yet implemented")
    }

    override fun dead() =
        hitPoints <= 0
}
