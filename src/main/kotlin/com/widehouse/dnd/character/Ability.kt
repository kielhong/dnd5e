package com.widehouse.dnd.character

open class Ability(private val score: Int) {
    companion object {
        fun builder(str: Int, dex: Int, con: Int, int: Int, wis: Int, cha: Int): Map<String, Ability> {
            return mapOf(
                "str" to Strength(str),
                "dex" to Dexterity(dex),
                "con" to Constitution(con),
                "int" to Intelligence(int),
                "wis" to Wisdom(wis),
                "cha" to Charisma(cha)
            )
        }
    }

    fun score() = score

    fun modifier() = (score - 10) / 2

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (score != (other as Ability).score) return false

        return true
    }

    override fun hashCode(): Int {
        return score
    }
}

class Strength(score: Int) : Ability(score)
class Dexterity(score: Int) : Ability(score)
class Constitution(score: Int) : Ability(score)
class Intelligence(score: Int) : Ability(score)
class Wisdom(score: Int) : Ability(score)
class Charisma(score: Int) : Ability(score)
