package com.widehouse.dnd.character.nonplayer

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.character.ability.Abilities
import com.widehouse.dnd.character.action.Action
import com.widehouse.dnd.item.Weapon

class Monster(
    override val name: String,
    override var abilities: Abilities,
    override var hitPoints: Int,
    val size: MonsterSize,
    val type: String,
    val armorClass: Int,
    private var action: Action = Action(0, Weapon(""))
) : Character(name, abilities, hitPoints) {
//
//    override fun attack(target: Creature): AttackResult {
//        return AttackResult(target, if (attackRoll(target)) dealDamage() else 0)
//    }
//
//    override fun getDamage(point: Int) {
//        hitPoints -= point
//    }
//
//    override fun dead() = hitPoints <= 0

    fun dealDamage(): Int {
        return action.weapon.damageRoll()
    }
}
