package com.example.health2.domain.usecase

import com.example.health2.domain.repository.UserStatsRepository
import com.example.health2.data.remote.dto.UserStatsDto

/**
 * UseCase permettant de récupérer les statistiques
 * d'activité physique d'un utilisateur.
 *
 * Les statistiques récupérées comprennent notamment
 * les données relatives au nombre de pas.
 */
class GetStepsUseCase(
    private val repository: UserStatsRepository
) {

    /**
     * Récupère les statistiques d'un utilisateur.
     *
     * @param userId Identifiant de l'utilisateur.
     * @return Liste des statistiques ou une erreur.
     */
    suspend operator fun invoke(
        userId: String
    ): Result<List<UserStatsDto>> {

        // Vérification de l'identifiant utilisateur
        if (userId.isBlank()) {
            return Result.failure(
                IllegalArgumentException(
                    "L'identifiant utilisateur est obligatoire."
                )
            )
        }

        // Récupération des statistiques
        return repository.getUserStats(userId)
    }
}
