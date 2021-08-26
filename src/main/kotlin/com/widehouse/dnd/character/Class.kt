package com.widehouse.dnd.character

import com.widehouse.dnd.dice.Die

enum class Class(val hitDice: Die) {
    Cleric(Die.D8),
    Fighter(Die.D20),
    Rogue(Die.D8),
    Wizard(Die.D6)
}
