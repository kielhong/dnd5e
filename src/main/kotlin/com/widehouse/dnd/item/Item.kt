package com.widehouse.dnd.item

sealed class Item(open val name: String, open val cost: Coin, open val weight: Int)
