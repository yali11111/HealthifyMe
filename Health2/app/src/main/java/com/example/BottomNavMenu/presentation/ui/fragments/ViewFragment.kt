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
 * Fragment permettant de consulter
 * les statistiques de santé.
 *
 * Les données seront ensuite récupérées
 * depuis le repository Health2.
 */
class ViewFragment : Fragment() {

    /**
     * Crée l'interface de consultation.
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

            setPadding(32, 40, 32, 32)

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        // Titre
        val title = TextView(requireContext()).apply {

            text = "Mes statistiques"

            textSize = 26f

            gravity = Gravity.CENTER

            setTextColor(Color.BLACK)

            setPadding(0, 0, 0, 32)
        }

        // Statistiques de poids
        val weight = createStatistic(
            "Poids",
            "Aucune donnée disponible"
        )

        // Statistiques de calories
        val calories = createStatistic(
            "Calories",
            "Aucune donnée disponible"
        )

        // Statistiques de sommeil
        val sleep = createStatistic(
            "Sommeil",
            "Aucune donnée disponible"
        )

        // Statistiques de pas
        val steps = createStatistic(
            "Nombre de pas",
            "Aucune donnée disponible"
        )

        // Statistiques cardiaques
        val heartRate = createStatistic(
            "Fréquence cardiaque",
            "Aucune donnée disponible"
        )

        // Ajout des composants
        layout.addView(title)

        layout.addView(weight)

        layout.addView(calories)

        layout.addView(sleep)

        layout.addView(steps)

        layout.addView(heartRate)

        return layout
    }

    /**
     * Crée un élément statistique.
     *
     * @param title Titre de la statistique.
     * @param value Valeur à afficher.
     * @return Élément TextView configuré.
     */
    private fun createStatistic(
        title: String,
        value: String
    ): TextView {

        return TextView(requireContext()).apply {

            text = "$title\n$value"

            textSize = 16f

            setTextColor(Color.BLACK)

            setBackgroundColor(Color.LTGRAY)

            gravity = Gravity.CENTER_VERTICAL

            setPadding(20, 24, 20, 24)

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(0, 8, 0, 8)

            layoutParams = params
        }
    }
}
