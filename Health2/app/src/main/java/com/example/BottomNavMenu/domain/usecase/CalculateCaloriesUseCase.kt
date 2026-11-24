package com.example.health2.domain.usecase

/**
 * UseCase permettant de calculer les besoins
 * énergétiques quotidiens d'un utilisateur.
 *
 * Formule utilisée : Mifflin-St Jeor.
 *
 * Les résultats sont des estimations et ne constituent
 * pas une prescription nutritionnelle.
 */
class CalculateCaloriesUseCase {

    /**
     * Sexe utilisé pour le calcul métabolique.
     */
    enum class Sex {
        MALE,
        FEMALE
    }

    /**
     * Résultat du calcul calorique.
     */
    data class CaloriesResult(
        val basalMetabolicRate: Double,
        val dailyCalories: Double,
        val activityFactor: Double
    )

    /**
     * Calcule le métabolisme basal et les besoins quotidiens.
     *
     * @param weightKg Poids en kilogrammes.
     * @param heightCm Taille en centimètres.
     * @param age Âge en années.
     * @param sex Sexe utilisé par la formule.
     * @param activityFactor Coefficient d'activité physique.
     */
    operator fun invoke(
        weightKg: Double,
        heightCm: Double,
        age: Int,
        sex: Sex,
        activityFactor: Double = 1.2
    ): CaloriesResult {

        // Vérification du poids
        require(weightKg.isFinite() && weightKg > 0.0) {
            "Le poids doit être positif."
        }

        // Vérification de la taille
        require(heightCm.isFinite() && heightCm > 0.0) {
            "La taille doit être positive."
        }

        // Vérification de l'âge
        require(age in 1..120) {
            "L'âge doit être compris entre 1 et 120 ans."
        }

        // Vérification du niveau d'activité
        require(
            activityFactor.isFinite() &&
            activityFactor in 1.0..2.5
        ) {
            "Coefficient d'activité invalide."
        }

        // Ajustement selon le sexe utilisé par la formule
        val sexAdjustment = when (sex) {
            Sex.MALE -> 5.0
            Sex.FEMALE -> -161.0
        }

        // Calcul du métabolisme basal
        val bmr =
            (10.0 * weightKg) +
            (6.25 * heightCm) -
            (5.0 * age) +
            sexAdjustment

        // Estimation des besoins énergétiques quotidiens
        val dailyCalories = bmr * activityFactor

        // Retour du résultat
        return CaloriesResult(
            basalMetabolicRate = bmr,
            dailyCalories = dailyCalories,
            activityFactor = activityFactor
        )
    }
}
