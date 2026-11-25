package com.example.BottomNavMenu.presentation.ui.screens.weight.state

data class WeightUiState(
    val initialWeight: Double? = null,
    val currentWeight: Double? = null,
    val targetWeight: Double? = null,
    val gainedWeight: Double = 0.0,
    val lostWeight: Double = 0.0,
    val progressPercentage: Float = 0f,
    val goalReached: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)
