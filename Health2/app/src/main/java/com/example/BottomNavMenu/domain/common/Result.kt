package com.example.BottomNavMenu.domain.common

/**
 * Resultat generique du domaine.
 */
sealed interface Result<out T> {

    data class Success<T>(
        val data: T
    ) : Result<T>

    data class Failure(
        val message: String
    ) : Result<Nothing>
}
