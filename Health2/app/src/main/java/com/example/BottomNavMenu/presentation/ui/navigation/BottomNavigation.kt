package com.example.BottomNavMenu.presentation.ui.navigation

import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.ui.setupWithNavController

/**
 * BottomNavigation
 *
 * Gère la barre de navigation inférieure
 * de l'application BottomNavMenu.
 */
object BottomNavigation {

    /**
     * Connecte la barre inférieure
     * au NavController.
     *
     * @param bottomNavigationView Barre de navigation.
     * @param navController Contrôleur de navigation.
     */
    fun setup(
        bottomNavigationView: BottomNavigationView,
        navController: NavController
    ) {

        // =====================================
        // CONFIGURATION DE LA NAVIGATION
        // =====================================

        bottomNavigationView.setupWithNavController(
            navController
        )
    }
}

