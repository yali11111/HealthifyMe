package com.example.health2.domain.exercise

/**
 * Modèle métier représentant un exercice des jambes.
 *
 * Regroupe les exercices ciblant les quadriceps,
 * les ischio-jambiers, les mollets et les fessiers.
 */
data class LegExercise(

    // Identifiant unique
    val id: String,

    // Nom de l'exercice
    val name: String,

    // Description
    val description: String,

    // Nombre de séries
    val sets: Int = 3,

    // Nombre de répétitions
    val repetitions: Int = 12,

    // Durée en secondes
    val durationSeconds: Int = 30,

    // Temps de repos
    val restSeconds: Int = 45,

    // Charge utilisée en kilogrammes
    val weightKg: Float? = null,

    // Calories estimées
    val estimatedCalories: Float? = null,

    // Niveau de difficulté
    val difficulty: String = "BEGINNER",

    // Muscles sollicités
    val targetMuscles: List<String> = listOf(
        "Quadriceps",
        "Ischio-jambiers",
        "Mollets",
        "Fessiers"
    ),

    // Matériel nécessaire
    val equipment: List<String> = emptyList(),

    // Instructions d'exécution
    val instructions: List<String> = emptyList(),

    // URL d'illustration
    val imageUrl: String? = null,

    // URL de démonstration vidéo
    val videoUrl: String? = null,

    // Disponibilité
    val isActive: Boolean = true

) {

    /**
     * Nombre total de répétitions.
     */
    val totalRepetitions: Int
        get() = sets * repetitions

    /**
     * Temps total de repos.
     */
    val totalRestTime: Int
        get() = (sets - 1).coerceAtLeast(0) * restSeconds

    /**
     * Vérification des données.
     */
    fun isValid(): Boolean {

        return id.isNotBlank() &&
                name.isNotBlank() &&
                sets > 0 &&
                repetitions >= 0 &&
                durationSeconds >= 0 &&
                restSeconds >= 0 &&
                (
                    weightKg == null ||
                    (
                        weightKg.isFinite() &&
                        weightKg >= 0f
                    )
                ) &&
                (
                    estimatedCalories == null ||
                    (
                        estimatedCalories.isFinite() &&
                        estimatedCalories >= 0f
                    )
                )
    }
}
