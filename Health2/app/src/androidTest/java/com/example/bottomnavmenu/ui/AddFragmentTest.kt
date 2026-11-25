package com.example.bottomnavmenu.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.BottomNavMenu.presentation.ui.fragments.AddFragment
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Test instrumenté de AddFragment.
 */
@RunWith(AndroidJUnit4::class)
class AddFragmentTest {

    @Test
    fun addFragmentShouldLaunch() {

        launchFragmentInContainer<AddFragment>(
            themeResId = com.example.BottomNavMenu.R.style.Theme_BottomNavMenu
        )
    }
}
