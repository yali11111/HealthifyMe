package com.example.BottomNavMenu.presentation.ui.screens.calories.state

data class CaloriesUiState(
    val weightKg: Double = 0.0,
    val heightCm: Double = 0.0,
    val age: Int = 0,
    val basalMetabolicRate: Double? = null,
    val dailyCalories: Double? = null,
    val activityFactor: Double = 1.2,
    val isLoading: Boolean = false,
    val error: String? = null
)
