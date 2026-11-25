package com.example.BottomNavMenu.core.common

/**
 * Resultat d'une operation reseau.
 */
sealed interface NetworkResult<out T> {

    data class Success<T>(
        val data: T
    ) : NetworkResult<T>

    data class Failure(
        val error: AppError
    ) : NetworkResult<Nothing>
}
