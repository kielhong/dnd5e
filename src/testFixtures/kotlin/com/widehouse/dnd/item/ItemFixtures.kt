package com.widehouse.dnd.item

import com.widehouse.dnd.dice.Die.D4
import com.widehouse.dnd.dice.Die.D8
import com.widehouse.dnd.item.WeaponProperty.Finesse
import com.widehouse.dnd.item.WeaponProperty.Light
import com.widehouse.dnd.item.WeaponProperty.Thrown
import com.widehouse.dnd.item.WeaponProperty.Versatile

class ItemFixtures {
    companion object {
        val dagger = Weapon("dagger", listOf(D4), "Melee Weapon", listOf(Finesse, Light, Thrown))
        val longSword = Weapon("LongSword", listOf(D8), "Melee Weapon", listOf(Versatile))
    }
}
