package com.widehouse.dnd

class Weapon(val name:String, val damage: List<Int>, val itemType: String) {
    fun damageRoll(): Int {
        var sum = 0
        for (dice in damage) {
            sum += Dice().roll(dice)
        }
        return sum;
    }
}