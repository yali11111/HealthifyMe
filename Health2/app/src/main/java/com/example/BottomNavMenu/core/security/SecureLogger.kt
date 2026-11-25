package com.example.BottomNavMenu.core.security

import android.util.Log

/**
 * Journalisation securisee.
 *
 * Les donnees personnelles et de sante
 * ne doivent jamais etre transmises au logger.
 */
object SecureLogger {

    private const val TAG = "BottomNavMenu"

    private val eventPattern =
        Regex("^[A-Z0-9_]{1,60}$")

    fun info(event: String) {

        if (SecurityConfig.DEBUG_LOGGING_ALLOWED &&
            eventPattern.matches(event)
        ) {
            Log.i(TAG, event)
        }
    }

    fun warning(event: String) {

        if (eventPattern.matches(event)) {
            Log.w(TAG, event)
        }
    }

    fun error(event: String) {

        if (eventPattern.matches(event)) {
            Log.e(TAG, event)
        }
    }
}
