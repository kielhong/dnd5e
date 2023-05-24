package com.widehouse.dnd.item

import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.item.DamageType.Bludgeoning
import com.widehouse.dnd.item.WeaponCategory.Simple
import com.widehouse.dnd.item.WeaponType.Melee

class Weapon(
    override val name: String,
    override val cost: Coin = Coin(0),
    override val weight: Int = 0,
    val category: WeaponCategory = Simple,
    val type: WeaponType = Melee,
    val damage: List<Dice> = emptyList(),
    val damageType: DamageType = Bludgeoning,
    val properties: List<WeaponProperty> = emptyList()
) : Item(name, cost, weight) {
    fun damageRoll() = damage.stream()
        .mapToInt { it.roll() }
        .sum()
}

sealed class WeaponCategory {
    object Simple : WeaponCategory()
    object Martial : WeaponCategory()
}

sealed class WeaponType {
    object Melee : WeaponType()
    object Range : WeaponType()
}

sealed class DamageType {
    object Bludgeoning : DamageType()
    object Slashing : DamageType()
    object Piercing : DamageType()
}

enum class WeaponProperty {
    Ammunition,
    Finesse,
    Light,
    Heavy,
    Range,
    Thrown,
    TwoHanded,
    Versatile
}
