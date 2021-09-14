package com.widehouse.dnd.challenge

class Challenge {
    companion object {
        fun challenge(dice: Int, modifiers: List<Int>, difficultyClass: Int): Boolean {
            return dice + modifiers.stream().mapToInt { it }.sum() >= difficultyClass
        }
    }
}
