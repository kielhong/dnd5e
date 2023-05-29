package com.widehouse.dnd.item

import com.widehouse.dnd.character.item.Armor
import com.widehouse.dnd.character.item.ArmorType
import com.widehouse.dnd.character.item.Coin
import com.widehouse.dnd.character.item.DamageType.Piercing
import com.widehouse.dnd.character.item.DamageType.Slashing
import com.widehouse.dnd.character.item.GP
import com.widehouse.dnd.character.item.Ring
import com.widehouse.dnd.character.item.Shield
import com.widehouse.dnd.character.item.Weapon
import com.widehouse.dnd.character.item.WeaponCategory.Martial
import com.widehouse.dnd.character.item.WeaponCategory.Simple
import com.widehouse.dnd.character.item.WeaponProperty
import com.widehouse.dnd.character.item.WeaponProperty.Finesse
import com.widehouse.dnd.character.item.WeaponProperty.Light
import com.widehouse.dnd.character.item.WeaponProperty.Thrown
import com.widehouse.dnd.character.item.WeaponType
import com.widehouse.dnd.character.item.WeaponType.Melee
import com.widehouse.dnd.dice.Dice

object ItemFixtures {
    // Weapon
    val dagger = Weapon(
        "Dagger",
        Coin(2, GP),
        1,
        Simple,
        Melee,
        listOf(Dice.D4),
        Piercing,
        listOf(Finesse, Light, Thrown)
    )
    val longSword = Weapon(
        "LongSword",
        Coin(15, GP),
        3,
        Martial,
        Melee,
        listOf(Dice.D8),
        Slashing,
        listOf(WeaponProperty.Versatile)
    )
    val longBow = Weapon(
        "LongBow",
        Coin(50, GP),
        2,
        Martial,
        WeaponType.Range,
        listOf(Dice.D8),
        Piercing,
        listOf(WeaponProperty.Ammunition, WeaponProperty.Heavy, WeaponProperty.Range, WeaponProperty.TwoHanded)
    )

    // Armor
    val padded = Armor("Padded Armor", ArmorType.LightArmor, 11, Coin(5, GP), 8)
    val breastplate = Armor("Breastplate", ArmorType.MediumArmor, 14, Coin(400, GP), 20)
    val chainMail = Armor("Chain Mail", ArmorType.HeavyArmor, 16, Coin(75, GP), 55)

    // shield
    val shield = Shield("Shield", 2, Coin(10, GP), 6)

    // accessory
    val ring = Ring("ring", Coin(10), 1)
}
