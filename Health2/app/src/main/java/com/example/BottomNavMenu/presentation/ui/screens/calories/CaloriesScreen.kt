
package com.example.health2.presentation.ui.screens.calories

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * Écran principal consacré au suivi calorique.
 *
 * Présente :
 * - Les besoins énergétiques quotidiens.
 * - Le métabolisme basal.
 * - Le niveau d'activité physique.
 */
class CaloriesScreen : Fragment() {

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val context = requireContext()

        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_HORIZONTAL
            setPadding(32, 40, 32, 32)
            setBackgroundColor(Color.WHITE)
        }

        // TITRE

        val title = TextView(context).apply {
            text = "Suivi calorique"
            textSize = 26f
            setTextColor(Color.BLACK)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 32)
        }

        // BESOINS ENERGETIQUES

        val dailyCalories = createCard(
            "Besoins énergétiques quotidiens",
            "Données non disponibles"
        )

        // METABOLISME BASAL

        val bmr = createCard(
            "Métabolisme basal",
            "Données non disponibles"
        )

        // ACTIVITE PHYSIQUE

        val activity = createCard(
            "Niveau d'activité",
            "À renseigner"
        )

        // DESCRIPTION

        val description = TextView(context).apply {
            text = "Suivez vos besoins énergétiques " +
                    "et votre évolution calorique."
            textSize = 16f
            setTextColor(Color.DKGRAY)
            setPadding(0, 24, 0, 16)
        }

        layout.addView(title)
        layout.addView(dailyCalories)
        layout.addView(bmr)
        layout.addView(activity)
        layout.addView(description)

        return layout
    }

    /**
     * Crée une carte d'information.
     */
    private fun createCard(
        title: String,
        value: String
    ): TextView {

        return TextView(requireContext()).apply {

            text = "$title\n$value"

            textSize = 17f
            setTextColor(Color.BLACK)

            setBackgroundColor(Color.LTGRAY)

            setPadding(24, 28, 24, 28)

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(0, 8, 0, 8)

            layoutParams = params
        }
    }
}
