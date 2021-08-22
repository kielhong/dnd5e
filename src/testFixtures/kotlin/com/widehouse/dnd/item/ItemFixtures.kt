package com.widehouse.dnd.item

import com.widehouse.dnd.dice.Die.D4
import com.widehouse.dnd.dice.Die.D8
import com.widehouse.dnd.item.ArmorType.HeavyArmor
import com.widehouse.dnd.item.ArmorType.LightArmor
import com.widehouse.dnd.item.ArmorType.MediumArmor
import com.widehouse.dnd.item.DamageType.Piercing
import com.widehouse.dnd.item.DamageType.Slashing
import com.widehouse.dnd.item.WeaponCategory.Martial
import com.widehouse.dnd.item.WeaponCategory.Simple
import com.widehouse.dnd.item.WeaponProperty.Ammunition
import com.widehouse.dnd.item.WeaponProperty.Finesse
import com.widehouse.dnd.item.WeaponProperty.Heavy
import com.widehouse.dnd.item.WeaponProperty.Light
import com.widehouse.dnd.item.WeaponProperty.Range
import com.widehouse.dnd.item.WeaponProperty.Thrown
import com.widehouse.dnd.item.WeaponProperty.TwoHanded
import com.widehouse.dnd.item.WeaponProperty.Versatile
import com.widehouse.dnd.item.WeaponType.Melee

class ItemFixtures {
    companion object {
        // Weapon
        val dagger = Weapon("Dagger", Simple, Melee, listOf(D4), Piercing, listOf(Finesse, Light, Thrown))
        val longSword = Weapon("LongSword", Martial, Melee, listOf(D8), Slashing, listOf(Versatile))
        val longBow = Weapon("LongBow", WeaponCategory.Martial, WeaponType.Range, listOf(D8), Piercing, listOf(Ammunition, Heavy, Range, TwoHanded))

        // Armor
        val padded = Armor("Padded Armor", LightArmor, 11)
        val breastplate = Armor("Breastplate", MediumArmor, 14)
        val chainMail = Armor("Chain Mail", HeavyArmor, 16)

        // shield
        val shield = Shield("Shield", 2)
    }
}
