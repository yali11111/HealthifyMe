package com.example.BottomNavMenu.domain.model

/**

Modèle métier représentant le suivi du poids.
Le poids est exprimé en kilogrammes.
La taille est exprimée en centimètres. */ 

data class WeightField(

// Poids actuel 
val currentWeight: Float,

// Poids précédent 
val previousWeight: Float? = null,

// Objectif de poids 
val targetWeight: Float? = null,

// Taille de l'utilisateur 
val heightCm: Float? = null,

// Date de la mesure 
val timestamp: Long = System.currentTimeMillis() ) {

/**

Différence entre le poids actuel et précédent. */ 
val weightDifference: Float? get() = previousWeight?.let { currentWeight - it }

/**

Calcule l'indice de masse corporelle. */ 
val bmi: Float? get() {
 val height = heightCm ?: return null

 if (
     !currentWeight.isFinite() ||
     !height.isFinite() ||
     currentWeight <= 0f ||
     height <= 0f
 ) {
     return null
 }

 val heightMeters = height / 100f

 return currentWeight /
         (heightMeters * heightMeters)


}

/**

Différence avec le poids cible. */ 
val remainingToGoal: Float? get() = targetWeight?.let { it - currentWeight }

/**

Indique si l'objectif de poids est atteint. */ 
val isGoalReached: Boolean get() = targetWeight != null && currentWeight == targetWeight

/**

Vérifie la validité des données. */ 
fun isValid(): Boolean {

return currentWeight.isFinite() && currentWeight > 0f && (previousWeight == null || (previousWeight.isFinite() && previousWeight > 0f)) && (targetWeight == null || (targetWeight.isFinite() && targetWeight > 0f)) && (heightCm == null || (heightCm.isFinite() && heightCm > 0f)) } } 
