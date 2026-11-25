package com.example.BottomNavMenu.presentation.ui.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

/**
 * Worker chargé de synchroniser
 * les données générales de santé.
 */
class HealthDataWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {

        return try {

            // TODO :
            // Synchroniser les données :
            // - Poids
            // - Calories
            // - Sommeil
            // - Activité physique

            Result.success()

        } catch (exception: Exception) {

            Result.retry()
        }
    }
}
