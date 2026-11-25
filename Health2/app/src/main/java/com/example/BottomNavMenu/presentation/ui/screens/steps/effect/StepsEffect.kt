package com.example.BottomNavMenu.presentation.ui.screens.steps.effect

sealed interface StepsEffect {

    data class ShowMessage(
        val message: String
    ) : StepsEffect

    data object ShowGoalReached : StepsEffect

    data object NavigateToHistory : StepsEffect
}
