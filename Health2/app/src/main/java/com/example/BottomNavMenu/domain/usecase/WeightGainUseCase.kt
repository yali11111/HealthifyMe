package com.example.health2.domain.usecase

/**
 * UseCase permettant de suivre un objectif de prise de poids.
 *
 * Les poids sont exprimés en kilogrammes.
 */
class WeightGainUseCase {

    /**
     * Résultat du suivi de prise de poids.
     */
    data class WeightGainResult(
        val currentWeight: Float,
        val targetWeight: Float,
        val gainedWeight: Float,
        val remainingWeight: Float,
        val progressPercentage: Float,
        val goalReached: Boolean
    )

    /**
     * Calcule la progression vers un objectif de prise de poids.
     *
     * @param initialWeight Poids de départ.
     * @param currentWeight Poids actuel.
     * @param targetWeight Poids souhaité.
     */
    operator fun invoke(
        initialWeight: Float,
        currentWeight: Float,
        targetWeight: Float
    ): WeightGainResult {

        // Vérification du poids initial
        require(initialWeight.isFinite() && initialWeight > 0f) {
            "Le poids initial est invalide."
        }

        // Vérification du poids actuel
        require(currentWeight.isFinite() && currentWeight > 0f) {
            "Le poids actuel est invalide."
        }

        // Vérification du poids cible
        require(
            targetWeight.isFinite() &&
            targetWeight > initialWeight
        ) {
            "Le poids cible doit être supérieur au poids initial."
        }

        // Quantité totale de poids à gagner
        val totalToGain = targetWeight - initialWeight

        // Quantité de poids gagnée
        val gained = currentWeight - initialWeight

        // Pourcentage de progression
        val progress = (
            gained / totalToGain
        ).coerceIn(0f, 1f) * 100f

        // Construction du résultat
        return WeightGainResult(
            currentWeight = currentWeight,
            targetWeight = targetWeight,
            gainedWeight = gained,
            remainingWeight = (targetWeight - currentWeight)
                .coerceAtLeast(0f),
            progressPercentage = progress,
            goalReached = currentWeight >= targetWeight
        )
    }
}
