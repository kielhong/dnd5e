package com.widehouse.dnd.character

class Party {
    companion object {
        const val MAX_PARTY_SIZE = 4
    }
    val members: MutableList<Character> = mutableListOf()

    fun join(character: Character) {
        if (members.size < MAX_PARTY_SIZE) {
            members.add(character)
        }
    }

    fun leave(character: Character) {
        members.remove(character)
    }
}
