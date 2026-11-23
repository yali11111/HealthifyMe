package com.example.BottomNavMenu.domain.model

/**

Modèle métier représentant les données caloriques.
Toutes les valeurs sont exprimées en kilocalories. */ 

data class CaloriesField(

// Calories consommées 
val consumedCalories: Float = 0f,

// Calories dépensées pendant les activités 
val burnedCalories: Float = 0f,

// Objectif calorique quotidien 
val dailyGoal: Float = 2000f,

// Date des données 
val timestamp: Long = System.currentTimeMillis() ) {

/**

Calories restantes par rapport à l'objectif. */ 
val remainingCalories: Float get() = (dailyGoal - consumedCalories).coerceAtLeast(0f)

/**

Pourcentage de l'objectif atteint. */ 
val progress: Float get() { if (dailyGoal <= 0f) return 0f
 return (
     consumedCalories / dailyGoal
 ).coerceIn(0f, 1f)


}

/**

Indique si l'objectif calorique est atteint. */ 
val isGoalReached: Boolean get() = dailyGoal > 0f && consumedCalories >= dailyGoal

/**

Vérifie la validité des données. */ 
fun isValid(): Boolean {

return consumedCalories.isFinite() && burnedCalories.isFinite() && dailyGoal.isFinite() && consumedCalories >= 0f && burnedCalories >= 0f && dailyGoal > 0f } } 
