package com.example.health2.presentation.ui.viewmodel

import androidx.lifecycle.ViewModel 
import com.example.health2.domain.usecase.CalculateCaloriesUseCase 
import dagger.hilt.android.lifecycle.HiltViewModel 
import javax.inject.Inject 
import kotlinx.coroutines.flow.MutableStateFlow 
import kotlinx.coroutines.flow.StateFlow 
import kotlinx.coroutines.flow.asStateFlow

/**

    ViewModel consacré au suivi calorique.
    Calcule le métabolisme basal
    et les besoins énergétiques quotidiens. */ 
    @HiltViewModel class CaloriesViewModel 
    @Inject constructor( private val calculateCaloriesUseCase: CalculateCaloriesUseCase ) : ViewModel() {

    /**
        État des informations caloriques. */ 
        data class CaloriesUiState( val basalMetabolicRate: Double? = null, val dailyCalories: Double? = null, val activityFactor: Double? = null, val error: String? = null )

    private val _uiState = MutableStateFlow(CaloriesUiState())

    val uiState: StateFlow<CaloriesUiState> = _uiState.asStateFlow()

    /**
        Calcule les besoins énergétiques. */ 
        fun calculateCalories( weightKg: Double, heightCm: Double, age: Int, sex: CalculateCaloriesUseCase.Sex, activityFactor: Double = 1.2 ) {

        try {

 val result = calculateCaloriesUseCase(
     weightKg = weightKg,
     heightCm = heightCm,
     age = age,
     sex = sex,
     activityFactor = activityFactor
 )

 _uiState.value = CaloriesUiState(
     basalMetabolicRate = result.basalMetabolicRate,
     dailyCalories = result.dailyCalories,
     activityFactor = result.activityFactor
 )

} catch (exception: IllegalArgumentException) {

         _uiState.value = CaloriesUiState(
             error = exception.message
                 ?: "Erreur lors du calcul calorique."
         )

        } }

    /**
        Réinitialise les données. */ 
        
        fun reset() { _uiState.value = CaloriesUiState() } } 
        
