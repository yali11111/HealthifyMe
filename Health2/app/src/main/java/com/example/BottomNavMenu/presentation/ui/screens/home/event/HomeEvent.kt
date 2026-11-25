package com.example.BottomNavMenu.presentation.ui.screens.home.event

sealed interface HomeEvent {

    data object LoadDashboard : HomeEvent

    data object RefreshDashboard : HomeEvent

    data class UpdateUserName(
        val name: String
    ) : HomeEvent

    data object OpenSteps : HomeEvent

    data object OpenWeight : HomeEvent

    data object OpenCalories : HomeEvent

    data object OpenSleep : HomeEvent
}
