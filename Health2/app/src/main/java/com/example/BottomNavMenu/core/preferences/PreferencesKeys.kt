package com.example.health2.core.preferences

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * Centralisation des clés DataStore.
 */
object PreferencesKeys {

    val THEME =
        stringPreferencesKey("theme")

    val LANGUAGE =
        stringPreferencesKey("language")

    val WEIGHT_UNIT =
        stringPreferencesKey("weight_unit")

    val DISTANCE_UNIT =
        stringPreferencesKey("distance_unit")

    val TIME_ZONE =
        stringPreferencesKey("time_zone")

    val ONBOARDING_COMPLETED =
        booleanPreferencesKey("onboarding_completed")

    val NOTIFICATIONS_ENABLED =
        booleanPreferencesKey("notifications_enabled")
}
