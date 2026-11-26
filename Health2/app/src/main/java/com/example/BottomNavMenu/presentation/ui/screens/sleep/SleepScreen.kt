package com.example.BottomNavMenu.presentation.ui.screens.sleep

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.health2.presentation.ui.viewmodel.SleepViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

/**
 * Écran principal consacré au suivi du sommeil.
 *
 * Présente les principales statistiques :
 * - Durée totale du sommeil.
 * - Sommeil profond.
 * - Sommeil léger.
 * - Sommeil paradoxal (REM).
 * - Durée d'éveil.
 * - Efficacité du sommeil.
 */
@AndroidEntryPoint
class SleepScreen : Fragment() {

    private val viewModel: SleepViewModel by viewModels()

    private lateinit var totalSleep: TextView
    private lateinit var deepSleep: TextView
    private lateinit var lightSleep: TextView
    private lateinit var remSleep: TextView
    private lateinit var awakeDuration: TextView
    private lateinit var efficiency: TextView
    private lateinit var errorMessage: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val scrollView = ScrollView(requireContext()).apply {
            fillViewport = true
            setBackgroundColor(Color.rgb(246, 248, 252))
        }

        val layout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 40, 32, 32)
        }

        // Titre principal
        val title = TextView(requireContext()).apply {
            text = "Suivi du sommeil"
            textSize = 27f
            setTextColor(Color.rgb(35, 45, 75))
            setTypeface(null, Typeface.BOLD)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 12)
        }

        // Sous-titre
        val subtitle = TextView(requireContext()).apply {
            text = "Analyse de votre repos nocturne"
            textSize = 15f
            setTextColor(Color.DKGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 28)
        }

        layout.addView(title)
        layout.addView(subtitle)

        // Cartes statistiques
        totalSleep = createCard("Durée totale")
        deepSleep = createCard("Sommeil profond")
        lightSleep = createCard("Sommeil léger")
        remSleep = createCard("Sommeil paradoxal (REM)")
        awakeDuration = createCard("Durée d'éveil")
        efficiency = createCard("Efficacité du sommeil")

        // Message d'erreur
        errorMessage = TextView(requireContext()).apply {
            textSize = 14f
            setTextColor(Color.rgb(200, 50, 50))
            visibility = View.GONE
            setPadding(8, 16, 8, 16)
        }

        layout.addView(totalSleep)
        layout.addView(deepSleep)
        layout.addView(lightSleep)
        layout.addView(remSleep)
        layout.addView(awakeDuration)
        layout.addView(efficiency)
        layout.addView(errorMessage)

        scrollView.addView(layout)

        return scrollView
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        observeSleepState()
    }

    /**
     * Observe les changements du StateFlow du ViewModel.
     */
    private fun observeSleepState() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {

                viewModel.uiState.collect { state ->

                    totalSleep.text = buildCardText(
                        "Durée totale",
                        formatDuration(state.totalDuration)
                    )

                    deepSleep.text = buildCardText(
                        "Sommeil profond",
                        formatDuration(state.deepSleep)
                    )

                    lightSleep.text = buildCardText(
                        "Sommeil léger",
                        formatDuration(state.lightSleep)
                    )

                    remSleep.text = buildCardText(
                        "Sommeil paradoxal (REM)",
                        formatDuration(state.remSleep)
                    )

                    awakeDuration.text = buildCardText(
                        "Durée d'éveil",
                        formatDuration(state.awakeDuration)
                    )

                    efficiency.text = buildCardText(
                        "Efficacité du sommeil",
                        formatPercentage(state.sleepEfficiency)
                    )

                    // Gestion des erreurs
                    val error = state.error

                    errorMessage.text = error.orEmpty()

                    errorMessage.visibility =
                        if (error.isNullOrBlank()) {
                            View.GONE
                        } else {
                            View.VISIBLE
                        }
                }
            }
        }
    }

    /**
     * Crée une carte statistique.
     */
    private fun createCard(title: String): TextView {

        return TextView(requireContext()).apply {

            text = "$title\n\nChargement..."

            textSize = 17f

            setTextColor(Color.rgb(40, 50, 70))

            gravity = Gravity.CENTER_VERTICAL

            setPadding(32, 32, 32, 32)

            background = GradientDrawable().apply {
                setColor(Color.WHITE)
                cornerRadius = 22f
                setStroke(1, Color.rgb(225, 232, 240))
            }

            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 8, 0, 8)
            }
        }
    }

    /**
     * Formate le contenu d'une carte.
     */
    private fun buildCardText(
        title: String,
        value: String
    ): String {
        return "$title\n\n$value"
    }

    /**
     * Formate un pourcentage.
     */
    private fun formatPercentage(value: Double): String {
        return String.format(
            Locale.FRANCE,
            "%.1f %%",
            value
        )
    }

    /**
     * Convertit une durée en minutes en heures et minutes.
     */
    private fun formatDuration(minutes: Int): String {

        if (minutes < 0) {
            return "Donnée invalide"
        }

        val hours = minutes / 60
        val remainingMinutes = minutes % 60

        return if (hours > 0) {
            "${hours} h ${remainingMinutes} min"
        } else {
            "${remainingMinutes} min"
        }
    }
}