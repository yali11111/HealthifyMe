package com.example.BottomNavMenu.presentation.ui.services

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * StepsCounter
 *
 * Récupère les données du capteur
 * de comptage des pas Android.
 */
class StepsCounter(
    context: Context
) {

    private val sensorManager =
        context.getSystemService(Context.SENSOR_SERVICE)
                as SensorManager

    private val stepSensor: Sensor? =
        sensorManager.getDefaultSensor(
            Sensor.TYPE_STEP_COUNTER
        )

    /**
     * Indique si le capteur est disponible.
     */
    fun isAvailable(): Boolean {
        return stepSensor != null
    }

    /**
     * Observe le nombre de pas fourni
     * par le capteur depuis son dernier démarrage.
     */
    fun observeSteps(): Flow<Long> = callbackFlow {

        val sensor = stepSensor

        if (sensor == null) {
            close(
                IllegalStateException(
                    "Capteur de pas indisponible."
                )
            )
            return@callbackFlow
        }

        val listener = object : SensorEventListener {

            override fun onSensorChanged(
                event: SensorEvent
            ) {
                if (event.sensor.type ==
                    Sensor.TYPE_STEP_COUNTER
                ) {
                    trySend(
                        event.values[0].toLong()
                    )
                }
            }

            override fun onAccuracyChanged(
                sensor: Sensor?,
                accuracy: Int
            ) = Unit
        }

        val registered = sensorManager.registerListener(
            listener,
            sensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )

        if (!registered) {
            close(
                IllegalStateException(
                    "Impossible d'activer le capteur."
                )
            )
            return@callbackFlow
        }

        awaitClose {
            sensorManager.unregisterListener(listener)
        }
    }
}
