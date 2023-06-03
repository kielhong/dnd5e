package com.widehouse.dnd.dice

class RollSituation(
    private val dice: List<Dice>,
    private val condition: RollCondition
) {
    companion object {
        fun of(dice: Dice) =
            RollSituation(listOf(dice), RollCondition.NORMAL)

        fun of(dice: List<Dice>, condition: RollCondition = RollCondition.NORMAL) =
            RollSituation(dice, condition)
    }

    fun roll(): Int {
        require(dice.isNotEmpty())

        val diceRolls = dice.map { it.roll() }
        return when (condition) {
            RollCondition.NORMAL -> diceRolls.first()
            RollCondition.ADVANTAGE -> diceRolls.max()
            RollCondition.DISADVANTAGE -> diceRolls.min()
        }
    }
}
