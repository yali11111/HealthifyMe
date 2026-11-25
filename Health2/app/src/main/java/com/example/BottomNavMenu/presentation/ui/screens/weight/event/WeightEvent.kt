package com.example.BottomNavMenu.presentation.ui.screens.weight.event

sealed interface WeightEvent {

    data class UpdateWeight(
        val weight: Double
    ) : WeightEvent

    data class SetTargetWeight(
        val target: Double
    ) : WeightEvent

    data object CalculateWeightGain : WeightEvent

    data object CalculateWeightLoss : WeightEvent

    data object LoadWeightHistory : WeightEvent

    data object Reset : WeightEvent
}
