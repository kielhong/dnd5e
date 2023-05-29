package com.widehouse.dnd.character.item

class Armor(
    override val name: String,
    val itemType: ArmorType,
    val armorClass: Int,
    override val cost: Coin = Coin(0),
    override val weight: Int = 0
) : Item(name, cost, weight)

enum class ArmorType {
    LightArmor,
    MediumArmor,
    HeavyArmor
}
