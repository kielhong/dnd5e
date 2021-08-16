package com.widehouse.dnd.combat

import com.widehouse.dnd.character.Character
import com.widehouse.dnd.dice.Dice
import com.widehouse.dnd.dice.Die

class Combat(val characters: List<Character>, val monsters: List<Character>) {
    private lateinit var roundOrder: List<Character>

    fun initiative() {
        roundOrder = characters + monsters
            .associateWith { Dice().roll(Die.D20) + it.ability["dex"]!!.modifier() }
            .toList()
            .sortedBy { (_, value) -> value }.reversed()
            .map { (key, _) -> key }
    }

    fun roundOrder(): List<Character> {
        return roundOrder
    }

    fun combat(): CombatResult {
        do {
            val roundResult = round()
        } while (!roundResult.endCombat())

        return CombatResult(characters, monsters)
    }

    fun round(): RoundResult {
        roundOrder.map {
            val turnResult = turn(it)
            roundOrder = turnResult.resolve()
        }
        return RoundResult(roundOrder, characters, monsters)
    }

    fun turn(character: Character): TurnResult {
        if (characters.contains(character)) {
            character.attack(monsters[0])
        }
        if (monsters.contains(character)) {
            character.attack(characters[0])
        }

        return TurnResult(roundOrder)
    }
}
