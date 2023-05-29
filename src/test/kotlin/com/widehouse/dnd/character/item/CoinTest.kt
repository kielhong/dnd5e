package com.widehouse.dnd.character.item

import com.widehouse.dnd.character.item.CP
import com.widehouse.dnd.character.item.Coin
import com.widehouse.dnd.character.item.EP
import com.widehouse.dnd.character.item.GP
import com.widehouse.dnd.character.item.PP
import com.widehouse.dnd.character.item.SP
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class CoinTest : FunSpec({
    test("plus test") {
        Coin(5) + Coin(10) shouldBe Coin(15)
        Coin(10) + Coin(10, SP) shouldBe Coin(110)
    }

    test("minus test") {
        Coin(10) - Coin(4) shouldBe Coin(6)
        Coin(10) - Coin(1, SP) shouldBe Coin(0)
    }

    test("exchange by another unit") {
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
