package com.widehouse.dnd.item

import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.item.DamageType.Bludgeoning

object Weapons {
    val Unarmed = Weapon("Unarmed", damage = listOf(Dice.D1), damageType = Bludgeoning)
}
