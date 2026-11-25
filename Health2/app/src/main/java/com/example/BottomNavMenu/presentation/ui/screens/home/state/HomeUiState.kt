package com.example.BottomNavMenu.presentation.ui.screens.home.state

data class HomeUiState(
    val userName: String = "",
    val totalSteps: Int = 0,
    val caloriesBurned: Double = 0.0,
    val currentWeight: Double? = null,
    val sleepDuration: Int = 0,
    val statistics: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
