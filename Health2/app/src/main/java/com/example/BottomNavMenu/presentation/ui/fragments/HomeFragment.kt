package com.example.health2.presentation.ui.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * Fragment principal de Health2.
 *
 * Présente un tableau de bord permettant
 * à l'utilisateur de consulter un résumé
 * de ses principales données de santé.
 */
class HomeFragment : Fragment() {

    /**
     * Crée l'interface du fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Conteneur principal
        val layout = LinearLayout(requireContext()).apply {

            orientation = LinearLayout.VERTICAL

            gravity = Gravity.CENTER_HORIZONTAL

            setPadding(32, 48, 32, 32)

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        // Titre principal
        val title = TextView(requireContext()).apply {

            text = "Bienvenue sur Health2"

            textSize = 26f

            gravity = Gravity.CENTER

            setTextColor(Color.BLACK)

            setPadding(0, 0, 0, 32)
        }

        // Description
        val description = TextView(requireContext()).apply {

            text = "Votre santé, votre bien-être, votre suivi."

            textSize = 16f

            gravity = Gravity.CENTER

            setTextColor(Color.DKGRAY)

            setPadding(0, 0, 0, 32)
        }

        // Section activité physique
        val steps = createHealthCard(
            "Activité physique",
            "Nombre de pas : données non disponibles"
        )

        // Section poids
        val weight = createHealthCard(
            "Poids",
            "Dernière mesure : données non disponibles"
        )

        // Section sommeil
        val sleep = createHealthCard(
            "Sommeil",
            "Durée : données non disponibles"
        )

        // Section fréquence cardiaque
        val heartRate = createHealthCard(
            "Fréquence cardiaque",
            "Dernière mesure : données non disponibles"
        )

        // Ajout des composants
        layout.addView(title)

        layout.addView(description)

        layout.addView(steps)

        layout.addView(weight)

        layout.addView(sleep)

        layout.addView(heartRate)

        return layout
    }

    /**
     * Crée une carte d'information de santé.
     *
     * @param title Titre de la carte.
     * @param description Description de la donnée.
     * @return Carte de santé sous forme de TextView.
     */
    private fun createHealthCard(
        title: String,
        description: String
    ): TextView {

        return TextView(requireContext()).apply {

            text = "$title\n$description"

            textSize = 16f

            setTextColor(Color.BLACK)

            setPadding(20, 24, 20, 24)

            gravity = Gravity.CENTER_VERTICAL

            setBackgroundColor(Color.LTGRAY)

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(0, 8, 0, 8)

            layoutParams = params
        }
    }
}
