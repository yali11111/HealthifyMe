package com.example.BottomNavMenu.presentation.ui.screens.steps.state

data class StepsUiState(
    val totalSteps: Int = 0,
    val dailyGoal: Int = 10000,
    val progressPercentage: Float = 0f,
    val distanceKm: Double = 0.0,
    val caloriesBurned: Double = 0.0,
    val isLoading: Boolean = false,
    val error: String? = null
)
