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
        val dagger = Weapon("Dagger", Simple, Melee, listOf(D4), Piercing, listOf(Finesse, Light, Thrown), Coin(2, GP), 1)
        val longSword = Weapon("LongSword", Martial, Melee, listOf(D8), Slashing, listOf(Versatile), Coin(15, GP), 3)
        val longBow = Weapon("LongBow", Martial, WeaponType.Range, listOf(D8), Piercing, listOf(Ammunition, Heavy, Range, TwoHanded), Coin(50, GP), 2)

        // Armor
        val padded = Armor("Padded Armor", LightArmor, 11, Coin(5, GP), 8)
        val breastplate = Armor("Breastplate", MediumArmor, 14, Coin(400, GP), 20)
        val chainMail = Armor("Chain Mail", HeavyArmor, 16, Coin(75, GP), 55)

        // shield
        val shield = Shield("Shield", 2, Coin(10, GP), 6)

        // accessory
        val ring = Ring("ring", Coin(10), 1)
    }
}
