package com.example.health2.domain.usecase

/**
 * UseCase permettant d'analyser la durée du sommeil.
 *
 * Toutes les durées sont exprimées en minutes.
 */
class CalculateSleepUseCase {

    /**
     * Résultat de l'analyse du sommeil.
     */
    data class SleepResult(
        val totalDuration: Int,
        val deepSleep: Int,
        val lightSleep: Int,
        val remSleep: Int,
        val awakeDuration: Int,
        val sleepEfficiency: Float,
        val deepSleepPercentage: Float,
        val remSleepPercentage: Float
    )

    /**
     * Analyse les différentes phases du sommeil.
     *
     * @param deepSleep Sommeil profond en minutes.
     * @param lightSleep Sommeil léger en minutes.
     * @param remSleep Sommeil paradoxal en minutes.
     * @param awakeDuration Temps d'éveil en minutes.
     */
    operator fun invoke(
        deepSleep: Int,
        lightSleep: Int,
        remSleep: Int,
        awakeDuration: Int = 0
    ): SleepResult {

        require(deepSleep >= 0) {
            "La durée du sommeil profond est invalide."
        }

        require(lightSleep >= 0) {
            "La durée du sommeil léger est invalide."
        }

        require(remSleep >= 0) {
            "La durée du sommeil paradoxal est invalide."
        }

        require(awakeDuration >= 0) {
            "La durée d'éveil est invalide."
        }

        // Durée totale du sommeil
        val totalSleep = deepSleep + lightSleep + remSleep

        // Durée totale passée au lit
        val totalNight = totalSleep + awakeDuration

        // Efficacité du sommeil
        val efficiency = if (totalNight > 0) {
            totalSleep.toFloat() / totalNight.toFloat() * 100f
        } else {
            0f
        }

        // Pourcentage de sommeil profond
        val deepPercentage = if (totalSleep > 0) {
            deepSleep.toFloat() / totalSleep.toFloat() * 100f
        } else {
            0f
        }

        // Pourcentage de sommeil paradoxal
        val remPercentage = if (totalSleep > 0) {
            remSleep.toFloat() / totalSleep.toFloat() * 100f
        } else {
            0f
        }

        // Retour du résultat
        return SleepResult(
            totalDuration = totalSleep,
            deepSleep = deepSleep,
            lightSleep = lightSleep,
            remSleep = remSleep,
            awakeDuration = awakeDuration,
            sleepEfficiency = efficiency,
            deepSleepPercentage = deepPercentage,
            remSleepPercentage = remPercentage
        )
    }
}
