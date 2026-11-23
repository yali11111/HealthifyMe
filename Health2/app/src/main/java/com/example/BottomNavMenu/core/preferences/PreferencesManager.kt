package com.example.health2.core.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Instance DataStore de Health2.
 */
private val Context.dataStore: DataStore<Preferences>
    by preferencesDataStore(
        name = "health2_preferences"
    )

/**
 * Gestion des préférences utilisateur.
 */
@Singleton
class PreferencesManager @Inject constructor(

    @ApplicationContext
    private val context: Context

) {

    // THEME

    val theme: Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.THEME] ?: "system"
        }

    suspend fun setTheme(value: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.THEME] = value
        }
    }

    // LANGUAGE

    val language: Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.LANGUAGE] ?: "fr"
        }

    suspend fun setLanguage(value: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.LANGUAGE] = value
        }
    }

    // WEIGHT UNIT

    val weightUnit: Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.WEIGHT_UNIT] ?: "kg"
        }

    suspend fun setWeightUnit(value: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.WEIGHT_UNIT] = value
        }
    }

    // DISTANCE UNIT

    val distanceUnit: Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.DISTANCE_UNIT] ?: "km"
        }

    suspend fun setDistanceUnit(value: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.DISTANCE_UNIT] = value
        }
    }

    // TIME ZONE

    val timeZone: Flow<String> =
        context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.TIME_ZONE]
                ?: "Europe/Paris"
        }

    suspend fun setTimeZone(value: String) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.TIME_ZONE] = value
        }
    }

    // ONBOARDING

    val onboardingCompleted: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.ONBOARDING_COMPLETED]
                ?: false
        }

    suspend fun setOnboardingCompleted(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.ONBOARDING_COMPLETED] = value
        }
    }

    // NOTIFICATIONS

    val notificationsEnabled: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.NOTIFICATIONS_ENABLED]
                ?: true
        }

    suspend fun setNotificationsEnabled(value: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.NOTIFICATIONS_ENABLED] = value
        }
    }

    // CLEAR PREFERENCES

    suspend fun clearPreferences() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
