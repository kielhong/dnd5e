package com.widehouse.dnd.item

class Ring(
    override val name: String,
    override val cost: Coin = Coin(0),
    override val weight: Int = 0
) : Item(name, cost, weight)
