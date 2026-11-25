package com.example.bottomnavmenu.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.BottomNavMenu.presentation.ui.fragments.HomeFragment
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertNotNull

/**
 * Test instrumenté de HomeFragment.
 */
@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @Test
    fun homeFragmentShouldLaunch() {

        launchFragmentInContainer<HomeFragment>(
            themeResId = com.example.BottomNavMenu.R.style.Theme_BottomNavMenu
        )
    }
}
