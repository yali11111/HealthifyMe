package com.example.BottomNavMenu.presentation.ui.screens.calories.effect

sealed interface CaloriesEffect {

    data class ShowMessage(
        val message: String
    ) : CaloriesEffect

    data object CalculationCompleted : CaloriesEffect

    data object NavigateBack : CaloriesEffect
}
