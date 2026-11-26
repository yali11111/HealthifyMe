package com.example.BottomNavMenu.domain.repository

import com.example.health2.data.remote.dto.UserStatsDto

/**
 * Contrat métier pour la gestion des statistiques de santé d'un utilisateur.
 */
interface UserStatsRepository {

    /**
     * Récupère toutes les statistiques de santé d'un utilisateur.
     *
     * @param userId Identifiant unique de l'utilisateur.
     * @return Un Result contenant la liste des statistiques ou une erreur.
     */
    suspend fun getUserStats(
        userId: String
    ): Result<List<UserStatsDto>>

    /**
     * Récupère une statistique de santé spécifique.
     *
     * @param userId Identifiant unique de l'utilisateur.
     * @param id Identifiant unique de la statistique.
     * @return Un Result contenant la statistique demandée ou une erreur.
     */
    suspend fun getUserStatById(
        userId: String,
        id: String
    ): Result<UserStatsDto>
}
