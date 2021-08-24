package com.widehouse.dnd.item

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CoinTest : FunSpec({
    test("add value") {
        val coin = Coin(5, CP)
        coin.add(Coin(2, CP))

        coin.value shouldBe Coin(7, CP).value
    }

    test("subtract value") {
        val coin = Coin(100, CP)
        coin.subtract(Coin(20, CP))

        coin.value shouldBe Coin(80, CP).value
    }

    test("add different unit coin") {
        val coin = Coin(5, SP)
        coin.add(Coin(10, CP))

        coin.value shouldBe Coin(60, CP).value
        coin.value shouldBe Coin(6, SP).value
    }

    test("exchange another unit") {
        val coin = Coin(10, GP)

        coin.exchange(CP) shouldBe 1000
        coin.exchange(SP) shouldBe 100
        coin.exchange(PP) shouldBe 1
    }

    test("coin unit") {
        Coin(5).value shouldBe Coin(5, CP).value
        Coin(5, SP).value shouldBe Coin(50, CP).value
        Coin(5, EP).value shouldBe Coin(250, CP).value
        Coin(5, GP).value shouldBe Coin(500, CP).value
        Coin(5, PP).value shouldBe Coin(5000, CP).value
    }
})
