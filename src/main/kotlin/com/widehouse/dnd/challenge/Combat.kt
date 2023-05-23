package com.widehouse.dnd.challenge

import com.widehouse.dnd.character.CharacterOld
import com.widehouse.dnd.character.Creature
import com.widehouse.dnd.character.Monster
import com.widehouse.dnd.dice.Dice

class Combat(val characterOlds: List<CharacterOld>, val monsters: List<Monster>) {
    private lateinit var roundOrder: List<Creature>

    fun initiative() {
        roundOrder = characterOlds + monsters
            .associateWith { Dice.D20.roll() + it.dexterity.modifier }
            .toList()
            .sortedBy { (_, value) -> value }.reversed()
            .map { (key, _) -> key }
    }

    fun roundOrder(): List<Creature> {
        return roundOrder
    }

    fun combat(): CombatResult {
        do {
            val roundResult = round()
        } while (!roundResult.endCombat())

        return CombatResult(characterOlds, monsters)
    }

    fun round(): RoundResult {
        roundOrder.map {
            val turnResult = turn(it)
            roundOrder = turnResult.resolve()
        }
        return RoundResult(roundOrder, characterOlds, monsters)
    }

    fun turn(character: Creature): TurnResult {
        if (characterOlds.contains(character)) {
            character.attack(monsters[0])
        }
        if (monsters.contains(character)) {
            character.attack(characterOlds[0])
        }

        return TurnResult(roundOrder)
    }
}
