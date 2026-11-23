package com.example.health2.core.extensions

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

/**
 * Intercepte les erreurs d'un Flow.
 *
 * Le consommateur décide du traitement
 * de l'erreur.
 */
fun <T> Flow<T>.handleErrors(
    onError: suspend (Throwable) -> Unit
): Flow<T> {

    return catch { exception ->
        onError(exception)
    }
}
