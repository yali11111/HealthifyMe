package com.example.BottomNavMenu.core.common

/**
 * Resultat de validation des formulaires.
 */
sealed interface ValidationResult {

    data object Valid : ValidationResult

    data class Invalid(
        val message: String
    ) : ValidationResult
}
