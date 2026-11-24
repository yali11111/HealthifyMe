
package com.example.health2.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel 
import com.example.health2.domain.usecase.WeightGainUseCase 
import com.example.health2.domain.usecase.WeightLossUseCase 
import dagger.hilt.android.lifecycle.HiltViewModel 
import javax.inject.Inject 
import kotlinx.coroutines.flow.MutableStateFlow 
import kotlinx.coroutines.flow.StateFlow 
import kotlinx.coroutines.flow.asStateFlow

/**

    ViewModel consacré au suivi du poids.
    Gère les objectifs de prise et de perte
    de poids de l'utilisateur. */ @HiltViewModel class WeightViewModel @Inject constructor( private val weightGainUseCase: WeightGainUseCase, private val weightLossUseCase: WeightLossUseCase ) : ViewModel() {

    /**
        État du suivi du poids. */ data class WeightUiState( val currentWeight: Float? = null, val targetWeight: Float? = null, val progressPercentage: Float = 0f, val gainedWeight: Float? = null, val lostWeight: Float? = null, val goalReached: Boolean = false, val error: String? = null )

    private val _uiState = MutableStateFlow(WeightUiState())

    val uiState: StateFlow<WeightUiState> = _uiState.asStateFlow()

    /**
        Calcule la progression d'une prise de poids. */ fun calculateWeightGain( initialWeight: Float, currentWeight: Float, targetWeight: Float ) {

        try {

 val result = weightGainUseCase(
     initialWeight = initialWeight,
     currentWeight = currentWeight,
     targetWeight = targetWeight
 )

 _uiState.value = WeightUiState(
     currentWeight = result.currentWeight,
     targetWeight = result.targetWeight,
     progressPercentage = result.progressPercentage,
     gainedWeight = result.gainedWeight,
     goalReached = result.goalReached
 )

} catch (exception: IllegalArgumentException) {

     _uiState.value = WeightUiState(
         error = exception.message
             ?: "Erreur lors du calcul de prise de poids."
     )

    } }

/**

    Calcule la progression d'une perte de poids. */ fun calculateWeightLoss( initialWeight: Float, currentWeight: Float, targetWeight: Float ) {

    try {

 val result = weightLossUseCase(
     initialWeight = initialWeight,
     currentWeight = currentWeight,
     targetWeight = targetWeight
 )

 _uiState.value = WeightUiState(
     currentWeight = result.currentWeight,
     targetWeight = result.targetWeight,
     progressPercentage = result.progressPercentage,
     lostWeight = result.lostWeight,
     goalReached = result.goalReached
 )

} catch (exception: IllegalArgumentException) {

         _uiState.value = WeightUiState(
             error = exception.message
                 ?: "Erreur lors du calcul de perte de poids."
         )

        } }

    /**
        Réinitialise le suivi du poids. */ 
        
        fun reset() { _uiState.value = WeightUiState() } } 
        
