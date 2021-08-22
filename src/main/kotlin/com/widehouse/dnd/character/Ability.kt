package com.widehouse.dnd.character

sealed class Ability(val score: Int) {
    val modifier: Int
        get() = (score - 10) / 2
}

class Strength(score: Int) : Ability(score)
class Dexterity(score: Int) : Ability(score)
class Constitution(score: Int) : Ability(score)
class Intelligence(score: Int) : Ability(score)
class Wisdom(score: Int) : Ability(score)
class Charisma(score: Int) : Ability(score)

class Abilities(val str: Int, val dex: Int, val con: Int, val int: Int, val wis: Int, val cha: Int)

sealed class AbilityType {
    object Strength : AbilityType()
    object Dexterity : AbilityType()
    object Constitution : AbilityType()
    object Intelligence : AbilityType()
    object Wisdom : AbilityType()
    object Charisma : AbilityType()
}
