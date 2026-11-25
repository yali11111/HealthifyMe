package com.example.BottomNavMenu.presentation.ui.screens.sleep.effect

sealed interface SleepEffect {

    data class ShowMessage(
        val message: String
    ) : SleepEffect

    data object AnalysisCompleted : SleepEffect

    data object NavigateToHistory : SleepEffect

    data object NavigateBack : SleepEffect
}
