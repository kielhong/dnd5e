package com.widehouse.dnd.character.ability

sealed class AbilityType {
    object Strength : AbilityType()
    object Dexterity : AbilityType()
    object Constitution : AbilityType()
    object Intelligence : AbilityType()
    object Wisdom : AbilityType()
    object Charisma : AbilityType()
}
