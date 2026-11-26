package com.example.health2.presentation.ui.screens.weight

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.health2.presentation.ui.viewmodel.WeightViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.util.Locale

/**
 * Écran principal consacré au suivi du poids.
 *
 * Présente :
 * - Poids actuel.
 * - Poids initial.
 * - Poids cible.
 * - Progression.
 * - Prise de poids.
 * - Perte de poids.
 */
@AndroidEntryPoint
class WeightScreen : Fragment() {

    private val viewModel: WeightViewModel by viewModels()

    private lateinit var currentWeight: TextView
    private lateinit var initialWeight: TextView
    private lateinit var targetWeight: TextView
    private lateinit var progressText: TextView
    private lateinit var gainedWeight: TextView
    private lateinit var lostWeight: TextView
    private lateinit var progressBar: ProgressBar
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
            text = "Suivi du poids"
            textSize = 27f
            setTextColor(Color.rgb(35, 45, 75))
            setTypeface(null, Typeface.BOLD)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 12)
        }

        // Sous-titre
        val subtitle = TextView(requireContext()).apply {
            text = "Suivez votre évolution corporelle"
            textSize = 15f
            setTextColor(Color.DKGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 0, 0, 28)
        }

        layout.addView(title)
        layout.addView(subtitle)

        // Cartes statistiques
        currentWeight = createCard("Poids actuel")
        initialWeight = createCard("Poids initial")
        targetWeight = createCard("Poids cible")
        progressText = createCard("Progression")
        gainedWeight = createCard("Poids gagné")
        lostWeight = createCard("Poids perdu")

        // Barre de progression
        progressBar = ProgressBar(
            requireContext(),
            null,
            android.R.attr.progressBarStyleHorizontal
        ).apply {

            max = 100
            progress = 0

            progressTintList =
                android.content.res.ColorStateList.valueOf(
                    Color.rgb(46, 160, 110)
                )

            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                24
            ).apply {
                setMargins(0, 12, 0, 20)
            }
        }

        // Message d'erreur
        errorMessage = TextView(requireContext()).apply {
            textSize = 14f
            setTextColor(Color.rgb(200, 50, 50))
            visibility = View.GONE
            setPadding(8, 16, 8, 16)
        }

        // Ajout des composants
        layout.addView(currentWeight)
        layout.addView(initialWeight)
        layout.addView(targetWeight)
        layout.addView(progressText)
        layout.addView(progressBar)
        layout.addView(gainedWeight)
        layout.addView(lostWeight)
        layout.addView(errorMessage)

        scrollView.addView(layout)

        return scrollView
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        observeWeightState()
    }

    /**
     * Observe les données du ViewModel.
     */
    private fun observeWeightState() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {

                viewModel.uiState.collect { state ->

                    currentWeight.text = buildCardText(
                        "Poids actuel",
                        formatWeight(state.currentWeight)
                    )

                    targetWeight.text = buildCardText(
                        "Poids cible",
                        formatWeight(state.targetWeight)
                    )

                    initialWeight.text = buildCardText(
                        "Poids initial",
                        "Consultez votre historique"
                    )

                    gainedWeight.text = buildCardText(
                        "Poids gagné",
                        formatWeight(state.gainedWeight)
                    )

                    lostWeight.text = buildCardText(
                        "Poids perdu",
                        formatWeight(state.lostWeight)
                    )

                    progressText.text = buildCardText(
                        "Progression",
                        formatPercentage(state.progressPercentage)
                    )

                    progressBar.progress =
                        state.progressPercentage
                            .coerceIn(0f, 100f)
                            .toInt()

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

            setPadding(24, 28, 24, 28)

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
     * Construit le contenu d'une carte.
     */
    private fun buildCardText(
        title: String,
        value: String
    ): String {
        return "$title\n\n$value"
    }

    /**
     * Formate le poids en kilogrammes.
     */
    private fun formatWeight(value: Float?): String {

        if (value == null) {
            return "Aucune donnée disponible"
        }

        return String.format(
            Locale.FRANCE,
            "%.1f kg",
            value
        )
    }

    /**
     * Formate le pourcentage de progression.
     */
    private fun formatPercentage(value: Float): String {

        return String.format(
            Locale.FRANCE,
            "%.1f %%",
            value
        )
    }
}
    
