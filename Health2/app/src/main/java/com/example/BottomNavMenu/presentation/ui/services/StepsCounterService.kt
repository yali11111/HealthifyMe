package com.example.BottomNavMenu.presentation.ui.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder

/**
 * StepsCounterService
 *
 * Service Android destiné au suivi
 * des pas en arrière-plan.
 *
 * Le branchement effectif du capteur
 * sera réalisé dans une étape dédiée.
 */
class StepsCounterService : Service() {

    companion object {

        const val CHANNEL_ID =
            "BottomNavMenu_steps_channel"

        const val NOTIFICATION_ID = 1001

        const val ACTION_START =
            "com.example.BottomNavMenu.ACTION_START_STEPS"

        const val ACTION_STOP =
            "com.example.BottomNavMenu.ACTION_STOP_STEPS"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    override fun onStartCommand(
        intent: Intent?,
        flags: Int,
        startId: Int
    ): Int {

        when (intent?.action) {

            ACTION_STOP -> {
                stopForeground(STOP_FOREGROUND_REMOVE)
                stopSelf()
                return START_NOT_STICKY
            }

            ACTION_START, null -> {
                startForeground(
                    NOTIFICATION_ID,
                    createNotification()
                )
            }

            else -> {
                stopSelf()
                return START_NOT_STICKY
            }
        }

        return START_STICKY
    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >=
            Build.VERSION_CODES.O
        ) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                "Suivi des pas BottomNavMenu",
                NotificationManager.IMPORTANCE_LOW
            ).apply {
                description =
                    "Suivi de l'activité physique."
            }

            val manager =
                getSystemService(
                    NotificationManager::class.java
                )

            manager.createNotificationChannel(channel)
        }
    }

    private fun createNotification(): Notification {

        val builder =
            if (Build.VERSION.SDK_INT >=
                Build.VERSION_CODES.O
            ) {
                Notification.Builder(this, CHANNEL_ID)
            } else {
                @Suppress("DEPRECATION")
                Notification.Builder(this)
            }

        return builder
            .setContentTitle("BottomNavMenu")
            .setContentText("Suivi des pas actif")
            .setSmallIcon(
                android.R.drawable.ic_menu_mylocation
            )
            .setOngoing(true)
            .build()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
