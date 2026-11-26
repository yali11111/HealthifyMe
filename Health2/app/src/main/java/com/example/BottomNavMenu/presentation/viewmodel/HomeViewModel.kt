package com.example.health2.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.health2.data.remote.dto.UserStatsDto
import com.example.health2.domain.usecase.GetStepsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel principal de Health2.
 *
 * Gère les informations affichées
 * sur le tableau de bord.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getStepsUseCase: GetStepsUseCase
) : ViewModel() {

    /**
     * État du tableau de bord.
     */
    data class HomeUiState(
        val statistics: List<UserStatsDto> = emptyList(),
        val isLoading: Boolean = false,
        val error: String? = null
    )

    private val _uiState = MutableStateFlow(HomeUiState())

    /**
     * État observable par l'interface utilisateur.
     */
    val uiState: StateFlow<HomeUiState> =
        _uiState.asStateFlow()

    /**
     * Charge les statistiques de l'utilisateur.
     *
     * @param userId Identifiant unique de l'utilisateur.
     */
    fun loadDashboard(userId: String) {

        // Vérification de l'identifiant utilisateur
        if (userId.isBlank()) {
            _uiState.value = HomeUiState(
                error = "Identifiant utilisateur invalide."
            )
            return
        }

        viewModelScope.launch {

            // Activation du chargement
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null
            )

            // Récupération des statistiques
            getStepsUseCase(userId)

                .onSuccess { statistics ->

                    _uiState.value = HomeUiState(
                        statistics = statistics,
                        isLoading = false,
                        error = null
                    )
                }

                .onFailure { exception ->

                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = exception.message
                            ?: "Impossible de charger le tableau de bord."
                    )
                }
        }
    }

    /**
     * Réinitialise les données du tableau de bord.
     */
    fun reset() {
        _uiState.value = HomeUiState()
    }
}