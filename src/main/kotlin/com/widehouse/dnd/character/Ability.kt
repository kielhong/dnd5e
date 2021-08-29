package com.widehouse.dnd.character

sealed class Ability(open val score: Int) {
    val modifier: Int
        get() = (score - 10) / 2
}

data class Strength(override val score: Int) : Ability(score)
data class Dexterity(override val score: Int) : Ability(score)
data class Constitution(override val score: Int) : Ability(score)
data class Intelligence(override val score: Int) : Ability(score)
data class Wisdom(override val score: Int) : Ability(score)
data class Charisma(override val score: Int) : Ability(score)

class Abilities(val str: Int, val dex: Int, val con: Int, val int: Int, val wis: Int, val cha: Int)

sealed class AbilityType {
    object Strength : AbilityType()
    object Dexterity : AbilityType()
    object Constitution : AbilityType()
    object Intelligence : AbilityType()
    object Wisdom : AbilityType()
    object Charisma : AbilityType()
}
