package com.widehouse.dnd.item

data class Coin(var value: Int, val unit: CoinUnit = CP) {
    init {
        value *= unit.rate
    }

    operator fun plus(increment: Coin): Coin {
        return Coin(value + increment.value)
    }

    operator fun minus(decrement: Coin): Coin {
        return Coin(value - decrement.value)
    }

    fun exchange(unit: CoinUnit): Int {
        return value / unit.rate
    }
}

sealed class CoinUnit(val rate: Int)
object CP : CoinUnit(1)
object SP : CoinUnit(10)
object EP : CoinUnit(50)
object GP : CoinUnit(100)
object PP : CoinUnit(1000)
