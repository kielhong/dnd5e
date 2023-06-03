package com.widehouse.dnd.character.ability

class Abilities(
    val strength: Ability,
    val dexterity: Ability,
    val constitution: Ability,
    val intelligence: Ability,
    val wisdom: Ability,
    val charisma: Ability
) {
    companion object {
        fun of(strScore: Int, dexScore: Int, conScore: Int, intScore: Int, wisScore: Int, chaScore: Int): Abilities {
            return Abilities(
                Ability.Strength(strScore),
                Ability.Dexterity(dexScore),
                Ability.Constitution(conScore),
                Ability.Intelligence(intScore),
                Ability.Wisdom(wisScore),
                Ability.Charisma(chaScore)
            )
        }
    }
}
