package com.widehouse.dnd.character.item

import com.widehouse.dnd.character.item.DamageType.Bludgeoning
import com.widehouse.dnd.dice.Dice

object Weapons {
    val Unarmed = Weapon("Unarmed", damage = listOf(Dice.D1), damageType = Bludgeoning)
}
