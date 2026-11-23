package com.example.health2.domain.exercise

/**
 * Modèle métier représentant un exercice abdominal.
 *
 * Utilisé pour organiser les exercices de la
 * zone abdominale dans Health2.
 */
data class AbsExercise(

    // Identifiant unique
    val id: String,

    // Nom de l'exercice
    val name: String,

    // Description de l'exercice
    val description: String,

    // Nombre de séries
    val sets: Int = 3,

    // Nombre de répétitions par série
    val repetitions: Int = 15,

    // Durée de l'exercice en secondes
    val durationSeconds: Int = 30,

    // Temps de repos entre les séries
    val restSeconds: Int = 30,

    // Calories estimées
    val estimatedCalories: Float? = null,

    // Niveau de difficulté
    val difficulty: String = "BEGINNER",

    // Principaux muscles sollicités
    val targetMuscles: List<String> = listOf("Abdominaux"),

    // Instructions d'exécution
    val instructions: List<String> = emptyList(),

    // URL d'illustration
    val imageUrl: String? = null,

    // URL de démonstration vidéo
    val videoUrl: String? = null,

    // Indique si l'exercice est disponible
    val isActive: Boolean = true

) {

    /**
     * Calcule le nombre total de répétitions.
     */
    val totalRepetitions: Int
        get() = sets * repetitions

    /**
     * Calcule le temps total de repos.
     */
    val totalRestTime: Int
        get() = (sets - 1).coerceAtLeast(0) * restSeconds

    /**
     * Vérifie la validité des données.
     */
    fun isValid(): Boolean {
        return id.isNotBlank() &&
                name.isNotBlank() &&
                sets > 0 &&
                repetitions >= 0 &&
                durationSeconds >= 0 &&
                restSeconds >= 0 &&
                (
                    estimatedCalories == null ||
                    (
                        estimatedCalories.isFinite() &&
                        estimatedCalories >= 0f
                    )
                )
    }
}
