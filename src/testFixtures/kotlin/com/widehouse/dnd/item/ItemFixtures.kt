package com.widehouse.dnd.item

import com.widehouse.dnd.dice.Die.D4
import com.widehouse.dnd.dice.Die.D8
import com.widehouse.dnd.item.ArmorType.HeavyArmor
import com.widehouse.dnd.item.ArmorType.LightArmor
import com.widehouse.dnd.item.ArmorType.MediumArmor
import com.widehouse.dnd.item.WeaponProperty.Finesse
import com.widehouse.dnd.item.WeaponProperty.Light
import com.widehouse.dnd.item.WeaponProperty.Thrown
import com.widehouse.dnd.item.WeaponProperty.Versatile

class ItemFixtures {
    companion object {
        // Weapon
        val dagger = Weapon("Dagger", listOf(D4), "Melee Weapon", listOf(Finesse, Light, Thrown))
        val longSword = Weapon("LongSword", listOf(D8), "Melee Weapon", listOf(Versatile))

        // Armor
        val padded = Armor("Padded Armor", LightArmor, 11)
        val breastplate = Armor("Breastplate", MediumArmor, 14)
        val chainMail = Armor("Chain Mail", HeavyArmor, 16)
    }
}
