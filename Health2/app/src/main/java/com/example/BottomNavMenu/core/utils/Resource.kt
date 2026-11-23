package com.example.health2.core.utils

/**
 * Représente les différents états d'une opération.
 *
 * Loading : chargement en cours.
 * Success : opération réussie.
 * Error : erreur rencontrée.
 */
sealed class Resource<out T> {

    data object Loading : Resource<Nothing>()

    data class Success<T>(
        val data: T
    ) : Resource<T>()

    data class Error(
        val message: String,
        val exception: Throwable? = null
    ) : Resource<Nothing>()
}
