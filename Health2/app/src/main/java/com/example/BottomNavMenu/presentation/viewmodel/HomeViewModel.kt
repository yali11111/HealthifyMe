
package com.example.health2.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel 
import androidx.lifecycle.viewModelScope 
import com.example.health2.data.remote.dto.UserStatsDto 
import com.example.health2.domain.usecase.GetStepsUseCase 
import dagger.hilt.android.lifecycle.HiltViewModel 
import javax.inject.Inject import kotlinx.coroutines.launch 
import kotlinx.coroutines.flow.MutableStateFlow 
import kotlinx.coroutines.flow.StateFlow 
import kotlinx.coroutines.flow.asStateFlow

/**

    ViewModel principal de Health2.
    Gère les informations affichées
    sur le tableau de bord. */ @HiltViewModel class HomeViewModel @Inject constructor( private val getStepsUseCase: GetStepsUseCase ) : ViewModel() {

    /**
        État du tableau de bord. */ 
        data class HomeUiState( val statistics: List<UserStatsDto> = emptyList(), val isLoading: Boolean = false, val error: String? = null )

    private val _uiState = MutableStateFlow(HomeUiState())

    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    /**
        Charge les statistiques de l'utilisateur. */ 
        fun loadDashboard(userId: String) {

        if (userId.isBlank()) { _uiState.value = HomeUiState( error = "Identifiant utilisateur invalide." ) return }

        viewModelScope.launch {

         _uiState.value = _uiState.value.copy(
             isLoading = true,
             error = null
         )

         getStepsUseCase(userId)
             .onSuccess { statistics ->

                 _uiState.value = HomeUiState(
                     statistics = statistics,
                     isLoading = false
                 )
             }
             .onFailure { exception ->

                 _uiState.value = HomeUiState(
                     isLoading = false,
                     error = exception.message
                         ?: "Impossible de charger le tableau de bord."
                 )
             }

        } }

    /**
        Réinitialise les données. */ fun reset() { _uiState.value = HomeUiState() } } 
        
