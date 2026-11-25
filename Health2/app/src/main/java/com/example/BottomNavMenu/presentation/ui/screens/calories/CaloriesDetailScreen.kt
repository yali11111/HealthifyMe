package com.example.health2.presentation.ui.screens.calories

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment

/**
 * Écran détaillé des informations caloriques.
 *
 * Présente :
 * - Le métabolisme basal.
 * - La dépense énergétique.
 * - Les besoins quotidiens.
 * - La formule de calcul.
 */
class CaloriesDetailScreen : Fragment() {

    companion object {
        private const val ARG_BMR = "bmr"
        private const val ARG_DAILY_CALORIES = "daily_calories"
        private const val ARG_ACTIVITY_FACTOR = "activity_factor"

        fun newInstance(
            bmr: Double,
            dailyCalories: Double,
            activityFactor: Double
        ): CaloriesDetailScreen {

            return CaloriesDetailScreen().apply {

                arguments = Bundle().apply {
                    putDouble(ARG_BMR, bmr)
                    putDouble(ARG_DAILY_CALORIES, dailyCalories)
                    putDouble(ARG_ACTIVITY_FACTOR, activityFactor)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: android.view.LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val bmr = arguments?.getDouble(ARG_BMR) ?: 0.0

        val dailyCalories =
            arguments?.getDouble(ARG_DAILY_CALORIES) ?: 0.0

        val activityFactor =
            arguments?.getDouble(ARG_ACTIVITY_FACTOR) ?: 1.2

        val scrollView = ScrollView(requireContext())

        val layout = LinearLayout(requireContext()).apply {

            orientation = LinearLayout.VERTICAL

            setPadding(32, 40, 32, 32)

            setBackgroundColor(Color.rgb(248, 250, 252))
        }

        val title = TextView(requireContext()).apply {

            text = "Détails des calories"

            textSize = 26f

            setTextColor(Color.rgb(25, 45, 75))

            setTypeface(null, Typeface.BOLD)

            gravity = Gravity.CENTER

            setPadding(0, 0, 0, 24)
        }

        layout.addView(title)

        layout.addView(
            createDetail(
                "Métabolisme basal",
                "Énergie nécessaire au fonctionnement " +
                    "de l'organisme au repos.",
                formatCalories(bmr)
            )
        )

        layout.addView(
            createDetail(
                "Dépense énergétique",
                "Facteur d'activité utilisé pour estimer " +
                    "les besoins énergétiques.",
                String.format(
                    java.util.Locale.FRANCE,
                    "%.2f",
                    activityFactor
                )
            )
        )

        layout.addView(
            createDetail(
                "Besoins quotidiens",
                "Estimation de l'énergie quotidienne " +
                    "nécessaire selon le profil et l'activité.",
                formatCalories(dailyCalories)
            )
        )

        layout.addView(
            createDetail(
                "Formule utilisée",
                "Mifflin-St Jeor : estimation du métabolisme " +
                    "basal à partir du poids, de la taille, " +
                    "de l'âge et du sexe."
            )
        )

        scrollView.addView(layout)

        return scrollView
    }

    private fun createDetail(
        title: String,
        description: String,
        value: String? = null
    ): TextView {

        return TextView(requireContext()).apply {

            text = buildString {

                append(title)
                append("\n\n")
                append(description)

                if (!value.isNullOrBlank()) {
                    append("\n\n")
                    append(value)
                }
            }

            textSize = 16f

            setTextColor(Color.rgb(40, 50, 65))

            setPadding(24, 28, 24, 28)

            background = GradientDrawable().apply {

                setColor(Color.WHITE)

                cornerRadius = 20f

                setStroke(
                    1,
                    Color.rgb(225, 232, 240)
                )
            }

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(0, 10, 0, 10)

            layoutParams = params
        }
    }

    private fun formatCalories(value: Double): String {

        return String.format(
            java.util.Locale.FRANCE,
            "%.0f kcal",
            value
        )
    }
}
 
    
