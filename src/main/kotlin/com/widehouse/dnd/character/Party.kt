package com.widehouse.dnd.character

class Party {
    companion object {
        const val MAX_PARTY_SIZE = 4
    }
    val members: MutableList<CharacterOld> = mutableListOf()

    fun join(characterOld: CharacterOld) {
        if (members.size < MAX_PARTY_SIZE) {
            members.add(characterOld)
        }
    }

    fun leave(characterOld: CharacterOld) {
        members.remove(characterOld)
    }
}
