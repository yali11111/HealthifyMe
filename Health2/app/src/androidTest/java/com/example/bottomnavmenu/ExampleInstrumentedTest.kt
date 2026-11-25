package com.example.bottomnavmenu

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test instrumenté général de BottomNavMenu.
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Test
    fun applicationContextShouldBeAvailable() {

        val context: Context =
            InstrumentationRegistry
                .getInstrumentation()
                .targetContext

        assertNotNull(context)
    }

    @Test
    fun applicationPackageShouldBeCorrect() {

        val context =
            InstrumentationRegistry
                .getInstrumentation()
                .targetContext

        assertEquals(
            "com.example.BottomNavMenu",
            context.packageName
        )
    }
}
