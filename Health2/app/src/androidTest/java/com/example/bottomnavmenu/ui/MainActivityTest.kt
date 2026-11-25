package com.example.bottomnavmenu.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.BottomNavMenu.presentation.ui.activities.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertNotNull

/**
 * Test instrumenté de MainActivity.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule =
        ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityShouldLaunch() {

        val activity = activityRule.activity

        assertNotNull(activity)
    }

    @Test
    fun mainActivityShouldHaveContent() {

        val activity = activityRule.activity

        assertNotNull(activity.window)
    }
}
