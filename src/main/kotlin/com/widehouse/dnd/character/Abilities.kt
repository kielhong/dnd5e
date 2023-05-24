package com.widehouse.dnd.character

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
                Strength(strScore),
                Dexterity(dexScore),
                Constitution(conScore),
                Intelligence(intScore),
                Wisdom(wisScore),
                Charisma(chaScore)
            )
        }
    }
}
