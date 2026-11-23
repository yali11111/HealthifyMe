package com.example.health2.domain.repository

import com.example.health2.data.remote.dto.ExerciseDto

/**
 * Contrat métier pour la gestion des exercices.
 *
 * Permet de récupérer les exercices disponibles
 * dans Health2 et de consulter leurs informations.
 */
interface ExerciseRepository {

    /**
     * Récupère la liste des exercices.
     *
     * @param category Catégorie facultative.
     * @return Liste des exercices ou une erreur.
     */
    suspend fun getExercises(
        category: String? = null
    ): Result<List<ExerciseDto>>

    /**
     * Récupère un exercice par son identifiant.
     *
     * @param id Identifiant de l'exercice.
     * @return Exercice demandé ou une erreur.
     */
    suspend fun getExerciseById(
        id: String
    ): Result<ExerciseDto>
}
