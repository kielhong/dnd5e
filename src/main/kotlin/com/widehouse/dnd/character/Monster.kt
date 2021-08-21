package com.widehouse.dnd.character

class Monster(
    val name: String,
    val size: String,
    val type: String,
    val hp: Int,
    val abilities: Abilities,
    val armorClass: Int) {

    val strength = Strength(abilities.str)
    val dexterity = Dexterity(abilities.dex)
    val constitution = Constitution(abilities.con)
    val intelligence = Intelligence(abilities.int)
    val wisdom = Wisdom(abilities.wis)
    val charisma = Charisma(abilities.cha)
}
