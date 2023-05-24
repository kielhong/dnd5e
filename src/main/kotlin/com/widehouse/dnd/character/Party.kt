package com.widehouse.dnd.character

class Party {
    companion object {
        const val MAX_PARTY_SIZE = 4
    }
    val members: MutableList<PlayerCharacter> = mutableListOf()

    fun join(playerCharacter: PlayerCharacter) {
        if (members.size < MAX_PARTY_SIZE) {
            members.add(playerCharacter)
        }
    }

    fun leave(playerCharacter: PlayerCharacter) {
        members.remove(playerCharacter)
    }
}
