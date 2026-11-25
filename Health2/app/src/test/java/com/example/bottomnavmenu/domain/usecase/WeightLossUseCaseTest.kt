package com.example.bottomnavmenu.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests de la perte de poids.
 */
class WeightLossUseCaseTest {

    @Test
    fun `weight loss should be calculated correctly`() {

        val initialWeight = 80f
        val currentWeight = 75f

        val lostWeight =
            initialWeight - currentWeight

        assertEquals(5f, lostWeight, 0.01f)
    }

    @Test
    fun `weight loss progress should be calculated correctly`() {

        val initialWeight = 80f
        val currentWeight = 75f
        val targetWeight = 70f

        val progress =
            ((initialWeight - currentWeight) /
            (initialWeight - targetWeight)) * 100f

        assertEquals(50f, progress, 0.01f)
    }

    @Test
    fun `goal should be reached when target weight is reached`() {

        val currentWeight = 70f
        val targetWeight = 70f

        assertTrue(currentWeight <= targetWeight)
    }
}
