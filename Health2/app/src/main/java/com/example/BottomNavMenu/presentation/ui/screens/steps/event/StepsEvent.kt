package com.example.BottomNavMenu.presentation.ui.screens.steps.event

sealed interface StepsEvent {

    data object LoadSteps : StepsEvent

    data object RefreshSteps : StepsEvent

    data class UpdateDailyGoal(
        val goal: Int
    ) : StepsEvent

    data object CalculateProgress : StepsEvent

    data object Reset : StepsEvent
}
