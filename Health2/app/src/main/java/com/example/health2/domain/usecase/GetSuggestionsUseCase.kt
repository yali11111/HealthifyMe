package com.example.health2.domain.usecase

import com.example.health2.domain.repository.SuggestionRepository
import com.example.health2.data.remote.dto.SuggestionDto

/**
 * UseCase permettant de récupérer les suggestions
 * personnalisées de santé.
 */
class GetSuggestionsUseCase(
    private val repository: SuggestionRepository
) {

    /**
     * Récupère les suggestions d'un utilisateur.
     *
     * @param userId Identifiant de l'utilisateur.
     * @return Liste des suggestions ou une erreur.
     */
    suspend operator fun invoke(
        userId: String
    ): Result<List<SuggestionDto>> {

        // Vérification de l'identifiant utilisateur
        if (userId.isBlank()) {
            return Result.failure(
                IllegalArgumentException(
                    "L'identifiant utilisateur est obligatoire."
                )
            )
        }

        // Récupération des suggestions
        return repository.getSuggestions(userId)
    }
}
