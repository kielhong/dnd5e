package com.widehouse.dnd.character.ability

sealed class Ability(open val score: Int) {
    val modifier: Int
        get() = (score - 10) / 2
}

class Strength(override val score: Int) : Ability(score)
class Dexterity(override val score: Int) : Ability(score)
class Constitution(override val score: Int) : Ability(score)
class Intelligence(override val score: Int) : Ability(score)
class Wisdom(override val score: Int) : Ability(score)
class Charisma(override val score: Int) : Ability(score)
