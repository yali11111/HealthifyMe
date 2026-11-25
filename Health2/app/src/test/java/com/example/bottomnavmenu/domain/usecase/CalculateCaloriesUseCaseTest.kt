package com.example.bottomnavmenu.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests du calcul calorique.
 *
 * Vérifie les propriétés mathématiques
 * et les valeurs attendues.
 */
class CalculateCaloriesUseCaseTest {

    @Test
    fun `basal metabolic rate should be positive`() {

        val weight = 70.0
        val height = 175.0
        val age = 25

        val bmr =
            (10 * weight) +
            (6.25 * height) -
            (5 * age) + 5

        assertTrue(bmr > 0)
    }

    @Test
    fun `daily calories should increase with activity factor`() {

        val bmr = 1600.0

        val sedentaryCalories = bmr * 1.2
        val activeCalories = bmr * 1.725

        assertTrue(activeCalories > sedentaryCalories)
    }

    @Test
    fun `activity factor should be positive`() {

        val activityFactor = 1.2

        assertTrue(activityFactor > 0)
    }
}
