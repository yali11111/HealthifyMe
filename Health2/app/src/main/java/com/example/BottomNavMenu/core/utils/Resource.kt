
package com.example.health2.core.utils

/**
 * Représente le résultat d'une opération exécutée
 * dans les différentes couches de Health2.
 *
 * @param T Type de la donnée retournée.
 */
sealed class Resource<out T> {

    /**
     * Opération en cours de chargement.
     *
     * @param data Donnée éventuellement disponible.
     */
    data class Loading<out T>(
        val data: T? = null
    ) : Resource<T>()

    /**
     * Opération terminée avec succès.
     *
     * @param data Résultat de l'opération.
     */
    data class Success<out T>(
        val data: T
    ) : Resource<T>()

    /**
     * Opération terminée avec une erreur.
     *
     * @param message Description de l'erreur.
     * @param throwable Exception éventuellement associée.
     * @param data Donnée éventuellement disponible.
     */
    data class Error<out T>(
        val message: String,
        val throwable: Throwable? = null,
        val data: T? = null
    ) : Resource<T>()
}
   
