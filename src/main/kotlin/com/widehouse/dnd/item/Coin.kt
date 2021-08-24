package com.widehouse.dnd.item

class Coin(var value: Int, unit: CoinUnit = CP) {
    init {
        value *= unit.rate
    }

    fun add(coin: Coin) {
        value += coin.value
    }

    fun subtract(coin: Coin) {
        value -= coin.value
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
