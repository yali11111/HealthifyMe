package com.example.bottomnavmenu.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Tests de la prise de poids.
 */
class WeightGainUseCaseTest {

    @Test
    fun `weight gain should be calculated correctly`() {

        val initialWeight = 60f
        val currentWeight = 65f

        val gainedWeight =
            currentWeight - initialWeight

        assertEquals(5f, gainedWeight, 0.01f)
    }

    @Test
    fun `weight gain progress should be calculated correctly`() {

        val initialWeight = 60f
        val currentWeight = 65f
        val targetWeight = 70f

        val progress =
            ((currentWeight - initialWeight) /
            (targetWeight - initialWeight)) * 100f

        assertEquals(50f, progress, 0.01f)
    }

    @Test
    fun `goal should be reached when target weight is reached`() {

        val currentWeight = 70f
        val targetWeight = 70f

        assertTrue(currentWeight >= targetWeight)
    }
}
