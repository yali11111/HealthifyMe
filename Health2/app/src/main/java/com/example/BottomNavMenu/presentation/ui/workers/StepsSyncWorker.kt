package com.example.BottomNavMenu.presentation.ui.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters

/**
 * Worker chargé de synchroniser
 * les données de pas.
 *
 * La connexion au Repository sera
 * ajoutée dans la couche DATA.
 */
class StepsSyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {

        return try {

            // TODO :
            // Connecter le Repository de pas.
            // Synchroniser les données locales
            // avec la source distante.

            Result.success()

        } catch (exception: Exception) {

            Result.retry()
        }
    }
}
