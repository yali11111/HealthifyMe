package com.example.BottomNavMenu.presentation.ui.screens.calories.event

sealed interface CaloriesEvent {

    data class UpdateWeight(
        val weight: Double
    ) : CaloriesEvent

    data class UpdateHeight(
        val height: Double
    ) : CaloriesEvent

    data class UpdateAge(
        val age: Int
    ) : CaloriesEvent

    data class UpdateActivityFactor(
        val factor: Double
    ) : CaloriesEvent

    data object CalculateCalories : CaloriesEvent

    data object Reset : CaloriesEvent
}
