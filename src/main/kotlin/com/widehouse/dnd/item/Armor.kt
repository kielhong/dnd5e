package com.widehouse.dnd.item

class Armor(name: String, val itemType: ArmorType, val armorClass: Int) : Item(name)

enum class ArmorType {
    LightArmor,
    MediumArmor,
    HeavyArmor
}
