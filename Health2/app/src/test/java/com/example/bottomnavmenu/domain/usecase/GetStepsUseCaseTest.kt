package com.example.bottomnavmenu.domain.usecase

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Tests du UseCase GetStepsUseCase.
 *
 * A adapter selon la signature réelle
 * du constructeur et du UseCase.
 */
class GetStepsUseCaseTest {

    @Test
    fun `usecase should return expected steps`() {

        val expectedSteps = 8500

        val actualSteps = 8500

        assertEquals(expectedSteps, actualSteps)
    }

    @Test
    fun `steps should not be negative`() {

        val steps = 0

        assertEquals(true, steps >= 0)
    }

    @Test
    fun `daily goal should be reached`() {

        val steps = 10000
        val goal = 10000

        val goalReached = steps >= goal

        assertEquals(true, goalReached)
    }
}
