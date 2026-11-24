
package com.example.health2.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.health2.domain.usecase.CalculateSleepUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel consacré à l'analyse du sommeil.
 *
 * Calcule :
 * - La durée totale du sommeil.
 * - Le sommeil profond.
 * - Le sommeil léger.
 * - Le sommeil paradoxal (REM).
 * - La durée d'éveil.
 * - L'efficacité du sommeil.
 * - Les pourcentages des phases de sommeil.
 */
@HiltViewModel
class SleepViewModel @Inject constructor(
    private val calculateSleepUseCase: CalculateSleepUseCase
) : ViewModel() {

    /**
     * État des statistiques du sommeil.
     */
    data class SleepUiState(
        val totalDuration: Int = 0,
        val deepSleep: Int = 0,
        val lightSleep: Int = 0,
        val remSleep: Int = 0,
        val awakeDuration: Int = 0,
        val sleepEfficiency: Float = 0f,
        val deepSleepPercentage: Float = 0f,
        val remSleepPercentage: Float = 0f,
        val error: String? = null
    )

    // État privé et modifiable.
    private val _uiState = MutableStateFlow(SleepUiState())

    // État public accessible depuis l'interface.
    val uiState: StateFlow<SleepUiState> = _uiState.asStateFlow()

    /**
     * Analyse les différentes phases du sommeil.
     */
    fun calculateSleep(
        deepSleep: Int,
        lightSleep: Int,
        remSleep: Int,
        awakeDuration: Int = 0
    ) {

        try {

            // Validation des données.
            if (
                deepSleep < 0 ||
                lightSleep < 0 ||
                remSleep < 0 ||
                awakeDuration < 0
            ) {
                throw IllegalArgumentException(
                    "Les durées du sommeil ne peuvent pas être négatives."
                )
            }

            // Calcul des statistiques.
            val result = calculateSleepUseCase(
                deepSleep = deepSleep,
                lightSleep = lightSleep,
                remSleep = remSleep,
                awakeDuration = awakeDuration
            )

            // Mise à jour de l'état.
            _uiState.value = SleepUiState(
                totalDuration = result.totalDuration,
                deepSleep = result.deepSleep,
                lightSleep = result.lightSleep,
                remSleep = result.remSleep,
                awakeDuration = result.awakeDuration,
                sleepEfficiency = result.sleepEfficiency,
                deepSleepPercentage = result.deepSleepPercentage,
                remSleepPercentage = result.remSleepPercentage,
                error = null
            )

        } catch (exception: IllegalArgumentException) {

            // Gestion des erreurs.
            _uiState.value = _uiState.value.copy(
                error = exception.message
                    ?: "Erreur lors de l'analyse du sommeil."
            )
        }
    }

    /**
     * Réinitialise les statistiques du sommeil.
     */
    fun reset() {
        _uiState.value = SleepUiState()
    }
}

