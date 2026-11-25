package com.example.BottomNavMenu.presentation.ui.screens.weight.effect

sealed interface WeightEffect {

    data class ShowMessage(
        val message: String
    ) : WeightEffect

    data object GoalReached : WeightEffect

    data object NavigateToHistory : WeightEffect

    data object NavigateBack : WeightEffect
}
