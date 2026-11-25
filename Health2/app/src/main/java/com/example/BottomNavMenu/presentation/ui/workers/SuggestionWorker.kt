package com.example.BottomNavMenu.presentation.ui.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

/**
 * Worker chargé d'actualiser
 * les suggestions de santé.
 */
class SuggestionWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {

        return try {

            // TODO :
            // Connecter SuggestionRepository.
            // Récupérer les nouvelles suggestions.

            Result.success()

        } catch (exception: Exception) {

            Result.retry()
        }
    }
}
