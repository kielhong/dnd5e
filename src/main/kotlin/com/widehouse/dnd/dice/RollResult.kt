package com.widehouse.dnd.dice

sealed class RollResult {
    object CriticalFail : RollResult()
    object Fail : RollResult()
    object Success : RollResult()
    object CriticalSuccess : RollResult()
}
