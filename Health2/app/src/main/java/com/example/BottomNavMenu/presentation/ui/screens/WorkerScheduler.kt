package com.example.BottomNavMenu.presentation.ui.workers

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.OneTimeWorkRequestBuilder
import java.util.concurrent.TimeUnit

/**
 * Centralise la planification
 * des tâches en arrière-plan.
 */
class WorkerScheduler(
    context: Context
) {

    private val workManager =
        WorkManager.getInstance(context.applicationContext)

    /**
     * Planifie la synchronisation des pas.
     */
    fun scheduleStepsSync() {

        val request =
            PeriodicWorkRequestBuilder<StepsSyncWorker>(
                15,
                TimeUnit.MINUTES
            )
                .build()

        workManager.enqueueUniquePeriodicWork(
            "BottomNavMenu_steps_sync",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }

    /**
     * Planifie l'actualisation des suggestions.
     */
    fun scheduleSuggestions() {

        val request =
            PeriodicWorkRequestBuilder<SuggestionWorker>(
                24,
                TimeUnit.HOURS
            )
                .build()

        workManager.enqueueUniquePeriodicWork(
            "BottomNavMenu_suggestions",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }

    /**
     * Planifie la synchronisation générale.
     */
    fun scheduleHealthSync() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(
                NetworkType.CONNECTED
            )
            .build()

        val request =
            PeriodicWorkRequestBuilder<HealthDataWorker>(
                6,
                TimeUnit.HOURS
            )
                .setConstraints(constraints)
                .build()

        workManager.enqueueUniquePeriodicWork(
            "BottomNavMenu_data_sync",
            ExistingPeriodicWorkPolicy.KEEP,
            request
        )
    }

    /**
     * Lance une synchronisation immédiate.
     */
    fun runImmediateHealthSync() {

        val request =
            OneTimeWorkRequestBuilder<HealthDataWorker>()
                .build()

        workManager.enqueue(request)
    }

    /**
     * Annule les tâches BottomNavMenu.
     */
    fun cancelAllWorkers() {

        workManager.cancelUniqueWork(
            "BottomNavMenu_steps_sync"
        )

        workManager.cancelUniqueWork(
            "BottomNavMenu_suggestions"
        )

        workManager.cancelUniqueWork(
            "BottomNavMenu_data_sync"
        )
    }

    /**
     * Programme toutes les tâches.
     */
    fun scheduleAll() {

        scheduleStepsSync()
        scheduleSuggestions()
        scheduleHealthSync()
    }
}
