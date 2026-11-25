package com.example.BottomNavMenu.presentation.ui.screens.sleep.event

sealed interface SleepEvent {

    data class UpdateDeepSleep(
        val duration: Int
    ) : SleepEvent

    data class UpdateLightSleep(
        val duration: Int
    ) : SleepEvent

    data class UpdateRemSleep(
        val duration: Int
    ) : SleepEvent

    data class UpdateAwakeDuration(
        val duration: Int
    ) : SleepEvent

    data object CalculateSleep : SleepEvent

    data object LoadSleepHistory : SleepEvent

    data object Reset : SleepEvent
}
