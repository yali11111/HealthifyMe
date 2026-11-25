package com.example.BottomNavMenu.presentation.ui.screens.sleep.state

data class SleepUiState(
    val deepSleep: Int = 0,
    val lightSleep: Int = 0,
    val remSleep: Int = 0,
    val awakeDuration: Int = 0,
    val totalDuration: Int = 0,
    val sleepEfficiency: Float = 0f,
    val deepSleepPercentage: Float = 0f,
    val remSleepPercentage: Float = 0f,
    val isLoading: Boolean = false,
    val error: String? = null
)
