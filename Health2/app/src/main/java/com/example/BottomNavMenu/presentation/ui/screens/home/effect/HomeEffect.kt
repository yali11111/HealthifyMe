package com.example.BottomNavMenu.presentation.ui.screens.home.effect

sealed interface HomeEffect {

    data class ShowMessage(
        val message: String
    ) : HomeEffect

    data object NavigateToSteps : HomeEffect

    data object NavigateToWeight : HomeEffect

    data object NavigateToCalories : HomeEffect

    data object NavigateToSleep : HomeEffect
}
