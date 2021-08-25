package com.widehouse.dnd.item

class Shield(
    override val name: String,
    val armorClass: Int = 2,
    override val cost: Coin = Coin(0),
    override val weight: Int = 0
) : Item(name, cost, weight)
