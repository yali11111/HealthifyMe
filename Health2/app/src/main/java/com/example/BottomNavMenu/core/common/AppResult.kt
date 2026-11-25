package com.example.BottomNavMenu.core.common

/**
 * Resultat generique de l'application.
 */
sealed interface AppResult<out T> {

    data class Success<T>(
        val data: T
    ) : AppResult<T>

    data class Error(
        val error: AppError
    ) : AppResult<Nothing>

    data object Loading : AppResult<Nothing>
}
