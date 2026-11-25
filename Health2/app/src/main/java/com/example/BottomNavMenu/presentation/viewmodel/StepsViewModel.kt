package com.example.health2.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.health2.data.remote.dto.UserStatsDto
import com.example.health2.domain.usecase.GetStepsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel consacré à l'activité physique.
 *
 * Permet de récupérer et d'afficher
 * les statistiques relatives aux pas effectués.
 */
@HiltViewModel
class StepsViewModel @Inject constructor(
    private val getStepsUseCase: GetStepsUseCase
) : ViewModel() {

    /**
     * État des statistiques de pas.
     */
    data class StepsUiState(
        val statistics: List<UserStatsDto> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )

    // État privé et modifiable.
    private val _uiState = MutableStateFlow(StepsUiState())

    // État public observable depuis l'interface.
    val uiState: StateFlow<StepsUiState> = _uiState.asStateFlow()

    /**
     * Récupère les statistiques d'activité physique.
     */
    fun loadSteps(userId: String) {

        // Vérification de l'identifiant utilisateur.
        if (userId.isBlank()) {
            _uiState.value = StepsUiState(
                error = "Identifiant utilisateur invalide."
            )
            return
        }

        viewModelScope.launch {

            // Activation du chargement.
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            try {

                // Récupération des statistiques.
                getStepsUseCase(userId)
                    .onSuccess { result ->

                        _uiState.value = StepsUiState(
                            statistics = result,
                            isLoading = false,
                            error = null
                        )
                    }
                    .onFailure { exception ->

                        // Ne pas intercepter les annulations de coroutine.
                        if (exception is CancellationException) {
                            throw exception
                        }

                        _uiState.value = StepsUiState(
                            statistics = emptyList(),
                            isLoading = false,
                            error = exception.message
                                ?: "Erreur lors du chargement des pas."
                        )
                    }

            } catch (exception: CancellationException) {

                throw exception

            } catch (exception: Exception) {

                // Gestion des erreurs inattendues.
                _uiState.value = StepsUiState(
                    statistics = emptyList(),
                    isLoading = false,
                    error = exception.message
                        ?: "Une erreur inattendue est survenue."
                )
            }
        }
    }

    /**
     * Efface les statistiques affichées
     * et réinitialise l'état.
     */
    fun clear() {
        _uiState.value = StepsUiState()
    }
}

