package com.example.health2.domain.usecase

/**
 * UseCase permettant de suivre un objectif de perte de poids.
 *
 * Les poids sont exprimés en kilogrammes.
 */
class WeightLossUseCase {

    /**
     * Résultat du suivi de perte de poids.
     */
    data class WeightLossResult(
        val currentWeight: Float,
        val targetWeight: Float,
        val lostWeight: Float,
        val remainingWeight: Float,
        val progressPercentage: Float,
        val goalReached: Boolean
    )

    /**
     * Calcule la progression vers un objectif de perte de poids.
     *
     * @param initialWeight Poids de départ.
     * @param currentWeight Poids actuel.
     * @param targetWeight Poids souhaité.
     */
    operator fun invoke(
        initialWeight: Float,
        currentWeight: Float,
        targetWeight: Float
    ): WeightLossResult {

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
            targetWeight > 0f &&
            targetWeight < initialWeight
        ) {
            "Le poids cible doit être positif et inférieur au poids initial."
        }

        // Quantité totale de poids à perdre
        val totalToLose = initialWeight - targetWeight

        // Quantité de poids perdue
        val lost = initialWeight - currentWeight

        // Pourcentage de progression
        val progress = (
            lost / totalToLose
        ).coerceIn(0f, 1f) * 100f

        // Construction du résultat
        return WeightLossResult(
            currentWeight = currentWeight,
            targetWeight = targetWeight,
            lostWeight = lost,
            remainingWeight = (currentWeight - targetWeight)
                .coerceAtLeast(0f),
            progressPercentage = progress,
            goalReached = currentWeight <= targetWeight
        )
    }
}
