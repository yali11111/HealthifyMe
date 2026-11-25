package com.example.BottomNavMenu.data.remote.mapper

import com.example.BottomNavMenu.data.remote.dto.response.ApiResponse

/**
 * Vérifie et extrait les données
 * d'une réponse API générique.
 */

fun <T> ApiResponse<T>.requireData(): T {

    if (!success) {
        throw IllegalStateException(
            message ?: "La requête API a échoué."
        )
    }

    return data ?: throw IllegalStateException(
        "La réponse API ne contient aucune donnée."
    )
}

/**
 * Vérifie le succès d'une réponse API.
 */

fun <T> ApiResponse<T>.isSuccessful(): Boolean {
    return success && data != null
}
