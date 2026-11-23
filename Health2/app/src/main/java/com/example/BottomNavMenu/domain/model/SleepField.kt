package com.example.BottomNavMenu.domain.model

/**

Modèle métier représentant les données du sommeil.
Les durées sont exprimées en minutes. */ 
data class SleepField(

// Durée totale du sommeil 
val totalSleepDuration: Int = 0,

// Sommeil profond 
val deepSleepDuration: Int = 0,

// Sommeil léger 
val lightSleepDuration: Int = 0,

// Sommeil paradoxal 
val remSleepDuration: Int = 0,

// Durée d'éveil pendant la nuit 
val awakeDuration: Int = 0,

// Heure de coucher (timestamp) 
val bedtime: Long? = null,

// Heure de réveil (timestamp) 
val wakeUpTime: Long? = null,

// Date de l'enregistrement 
val timestamp: Long = System.currentTimeMillis() ) {

/**

Total des phases de sommeil renseignées. */ 
val calculatedSleepDuration: Int get() = deepSleepDuration + lightSleepDuration + remSleepDuration

/**

Durée totale incluant les périodes d'éveil. */ 
val totalNightDuration: Int get() = totalSleepDuration + awakeDuration

/**

Pourcentage de sommeil profond. */ 
val deepSleepPercentage: Float get() { if (totalSleepDuration <= 0) return 0f
 return (
     deepSleepDuration.toFloat() /
     totalSleepDuration.toFloat()
 ) * 100f


}

/**

Pourcentage de sommeil paradoxal. */ 
val remSleepPercentage: Float get() { if (totalSleepDuration <= 0) return 0f
 return (
     remSleepDuration.toFloat() /
     totalSleepDuration.toFloat()
 ) * 100f


}

/**

Vérifie la validité des durées. */ 
fun isValid(): Boolean {

return totalSleepDuration >= 0 && deepSleepDuration >= 0 && lightSleepDuration >= 0 && remSleepDuration >= 0 && awakeDuration >= 0 } } 
