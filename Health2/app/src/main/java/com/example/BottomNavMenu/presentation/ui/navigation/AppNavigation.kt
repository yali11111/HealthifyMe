package com.example.BottomNavMenu.presentation.ui.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.BottomNavMenu.R
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * AppNavigation
 *
 * Point central de configuration
 * de la navigation de BottomNavMenu.
 */
object AppNavigation {

    /**
     * Initialise la navigation générale.
     *
     * @param activity Activité principale.
     * @param navHostId Identifiant du NavHostFragment.
     * @param bottomNavigationId Identifiant de la barre inférieure.
     */
    fun setup(
        activity: FragmentActivity,
        @IdRes navHostId: Int,
        @IdRes bottomNavigationId: Int
    ) {

        // =====================================
        // 1. RECUPERATION DU NAVHOST
        // =====================================

        val navHostFragment =
            activity.supportFragmentManager
                .findFragmentById(navHostId) as? NavHostFragment
                ?: throw IllegalStateException(
                    "NavHostFragment introuvable."
                )

        // =====================================
        // 2. RECUPERATION DU NAVCONTROLLER
        // =====================================

        val navController =
            navHostFragment.navController

        // =====================================
        // 3. RECUPERATION BOTTOM NAVIGATION
        // =====================================

        val bottomNavigation =
            activity.findViewById<BottomNavigationView>(
                bottomNavigationId
            ) ?: throw IllegalStateException(
                "BottomNavigationView introuvable."
            )

        // =====================================
        // 4. CONFIGURATION NAVIGATION
        // =====================================

        BottomNavigation.setup(
            bottomNavigationView = bottomNavigation,
            navController = navController
        )
    }
}

