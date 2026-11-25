
package com.example.BottomNavMenu.presentation.ui.navigation

/**
 * NavigationRoutes
 *
 * Centralise les destinations de navigation
 * de l'application BottomNavMenu.
 */
object NavigationRoutes {

    // =====================================
    // DESTINATIONS PRINCIPALES
    // =====================================

    const val HOME = "home"

    const val ADD = "add"

    const val VIEW = "view"

    const val PROFILE = "profile"

    // =====================================
    // ACTIVITE PHYSIQUE
    // =====================================

    const val STEPS = "steps"

    // =====================================
    // SUIVI DU POIDS
    // =====================================

    const val WEIGHT = "weight"

    // =====================================
    // BESOINS ENERGETIQUES
    // =====================================

    const val CALORIES = "calories"

    // =====================================
    // SOMMEIL
    // =====================================

    const val SLEEP = "sleep"

    // =====================================
    // SUGGESTIONS
    // =====================================

    const val SUGGESTIONS = "suggestions"

    // =====================================
    // PARAMETRES
    // =====================================

    const val SETTINGS = "settings"

    // =====================================
    // DESTINATION DE DEMARRAGE
    // =====================================

    const val START_DESTINATION = HOME

    /**
     * Vérifie si une route correspond
     * à une destination principale.
     */
    fun isMainDestination(route: String?): Boolean {

        return when (route) {

            HOME,
            ADD,
            VIEW,
            PROFILE -> true

            else -> false
        }
    }
}

