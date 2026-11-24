
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
import android.widget.ScrollView
import android.widget.TextView
import android.widget.ProgressBar

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle

import com.example.health2.presentation.ui.viewmodel.WeightViewModel

import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.launch

/**
 * Écran principal consacré au suivi du poids.
 *
 * Présente :
 * - Poids actuel.
 * - Poids initial.
 * - Poids cible.
 * - Progression.
 * - Prise ou perte de poids.
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

        val scrollView = ScrollView(requireContext())

        val layout = LinearLayout(requireContext()).apply {

            orientation = LinearLayout.VERTICAL

            setPadding(32, 40, 32, 32)

            setBackgroundColor(
                Color.rgb(246, 248, 252)
            )
        }

        // =====================================
        // TITRE
        // =====================================

        val title = TextView(requireContext()).apply {

            text = "Suivi du poids"

            textSize = 27f

            setTextColor(
                Color.rgb(35, 45, 75)
            )

            setTypeface(null, Typeface.BOLD)

            gravity = Gravity.CENTER

            setPadding(0, 0, 0, 12)
        }

        val subtitle = TextView(requireContext()).apply {

            text = "Suivez votre évolution corporelle"

            textSize = 15f

            setTextColor(Color.DKGRAY)

            gravity = Gravity.CENTER

            setPadding(0, 0, 0, 28)
        }

        layout.addView(title)
        layout.addView(subtitle)

        // =====================================
        // POIDS ACTUEL
        // =====================================

        currentWeight = createCard(
            "Poids actuel",
            "Aucune donnée disponible"
        )

        // =====================================
        // POIDS INITIAL
        // =====================================

        initialWeight = createCard(
            "Poids initial",
            "Aucune donnée disponible"
        )

        // =====================================
        // POIDS CIBLE
        // =====================================

        targetWeight = createCard(
            "Poids cible",
            "Aucun objectif défini"
        )

        // =====================================
        // PROGRESSION
        // =====================================

        progressText = createCard(
            "Progression",
            "Aucune progression disponible"
        )

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

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                24
            )

            params.setMargins(0, 12, 0, 20)

            layoutParams = params
        }

        // =====================================
        // PRISE DE POIDS
        // =====================================

        gainedWeight = createCard(
            "Poids gagné",
            "Aucune donnée disponible"
        )

        // =====================================
        // PERTE DE POIDS
        // =====================================

        lostWeight = createCard(
            "Poids perdu",
            "Aucune donnée disponible"
        )

        // =====================================
        // ERREURS
        // =====================================

        errorMessage = TextView(requireContext()).apply {

            textSize = 14f

            setTextColor(Color.RED)

            visibility = View.GONE

            setPadding(8, 16, 8, 16)
        }

        // =====================================
        // AJOUT DES ELEMENTS
        // =====================================

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

    // =========================================
    // OBSERVATION DU VIEWMODEL
    // =========================================

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        observeWeightState()
    }

    private fun observeWeightState() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {

                viewModel.uiState.collect { state ->

                    currentWeight.text =
                        "Poids actuel\n\n" +
                        formatWeight(state.currentWeight)

                    targetWeight.text =
                        "Poids cible\n\n" +
                        formatWeight(state.targetWeight)

                    progressText.text =
                        "Progression\n\n" +
                        String.format(
                            java.util.Locale.FRANCE,
                            "%.1f %%",
                            state.progressPercentage
                        )

                    initialWeight.text =
                        "Poids initial\n\n" +
                        "Consultez votre historique"

                    gainedWeight.text =
                        "Poids gagné\n\n" +
                        formatWeight(state.gainedWeight)

                    lostWeight.text =
                        "Poids perdu\n\n" +
                        formatWeight(state.lostWeight)

                    progressBar.progress =
                        state.progressPercentage
                            .coerceIn(0f, 100f)
                            .toInt()

                    errorMessage.text =
                        state.error ?: ""

                    errorMessage.visibility =
                        if (state.error != null) {
                            View.VISIBLE
                        } else {
                            View.GONE
                        }
                }
            }
        }
    }

    // =========================================
    // CREATION DES CARTES
    // =========================================

    private fun createCard(
        title: String,
        value: String
    ): TextView {

        return TextView(requireContext()).apply {

            text = "$title\n\n$value"

            textSize = 17f

            setTextColor(
                Color.rgb(40, 50, 70)
            )

            setPadding(24, 28, 24, 28)

            background = GradientDrawable().apply {

                setColor(Color.WHITE)

                cornerRadius = 22f

                setStroke(
                    1,
                    Color.rgb(225, 232, 240)
                )
            }

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(0, 8, 0, 8)

            layoutParams = params
        }
    }

    // =========================================
    // FORMATAGE DU POIDS
    // =========================================

    private fun formatWeight(value: Float?): String {

        if (value == null) {
            return "Aucune donnée disponible"
        }

        return String.format(
            java.util.Locale.FRANCE,
            "%.1f kg",
            value
        )
    }
}

    
    EOF

echo "[5/6] WeightScreen.kt OK"
# ==========================================
# 6. WEIGHT DETAIL SCREEN
# ==========================================

cat > "$BASE/weight/WeightDetailScreen.kt" <<'EOF' 
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
import android.widget.ScrollView
import android.widget.TextView
import android.widget.ProgressBar

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle

import com.example.health2.presentation.ui.viewmodel.WeightViewModel

import dagger.hilt.android.AndroidEntryPoint

import kotlinx.coroutines.launch

/**
 * Écran détaillé du suivi du poids.
 *
 * Présente :
 * - Prise de poids.
 * - Perte de poids.
 * - Progression.
 * - Objectifs.
 * - Historique.
 * - Évolution.
 */

@AndroidEntryPoint
class WeightDetailScreen : Fragment() {

    private val viewModel: WeightViewModel by viewModels()

    private lateinit var gainCard: TextView
    private lateinit var lossCard: TextView
    private lateinit var currentCard: TextView
    private lateinit var targetCard: TextView
    private lateinit var progressCard: TextView
    private lateinit var historyCard: TextView
    private lateinit var evolutionCard: TextView

    private lateinit var progressBar: ProgressBar
    private lateinit var errorMessage: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val scrollView = ScrollView(requireContext())

        val layout = LinearLayout(requireContext()).apply {

            orientation = LinearLayout.VERTICAL

            setPadding(32, 40, 32, 32)

            setBackgroundColor(
                Color.rgb(246, 248, 252)
            )
        }

        // =====================================
        // TITRE
        // =====================================

        val title = TextView(requireContext()).apply {

            text = "Détails du poids"

            textSize = 27f

            setTextColor(
                Color.rgb(35, 45, 75)
            )

            setTypeface(null, Typeface.BOLD)

            gravity = Gravity.CENTER

            setPadding(0, 0, 0, 12)
        }

        val subtitle = TextView(requireContext()).apply {

            text = "Analyse de votre progression corporelle"

            textSize = 14f

            setTextColor(Color.DKGRAY)

            gravity = Gravity.CENTER

            setPadding(0, 0, 0, 28)
        }

        layout.addView(title)
        layout.addView(subtitle)

        // =====================================
        // POIDS ACTUEL
        // =====================================

        currentCard = createDetail(
            "Poids actuel",
            "Aucune mesure disponible"
        )

        // =====================================
        // OBJECTIF
        // =====================================

        targetCard = createDetail(
            "Objectif de poids",
            "Aucun objectif défini"
        )

        // =====================================
        // PRISE DE POIDS
        // =====================================

        gainCard = createDetail(
            "Prise de poids",
            "Suivi de la progression vers un objectif de prise de poids."
        )

        // =====================================
        // PERTE DE POIDS
        // =====================================

        lossCard = createDetail(
            "Perte de poids",
            "Suivi de la progression vers un objectif de perte de poids."
        )

        // =====================================
        // PROGRESSION
        // =====================================

        progressCard = createDetail(
            "Progression",
            "Aucune progression disponible"
        )

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

        // =====================================
        // HISTORIQUE
        // =====================================

        historyCard = createDetail(
            "Historique",
            "Consultez vos anciennes mesures de poids."
        )

        // =====================================
        // EVOLUTION
        // =====================================

        evolutionCard = createDetail(
            "Évolution",
            "Visualisez les changements de votre poids dans le temps."
        )

        // =====================================
        // ERREURS
        // =====================================

        errorMessage = TextView(requireContext()).apply {

            textSize = 14f

            setTextColor(Color.RED)

            visibility = View.GONE

            setPadding(8, 16, 8, 16)
        }

        // =====================================
        // AJOUT DES ELEMENTS
        // =====================================

        layout.addView(currentCard)
        layout.addView(targetCard)
        layout.addView(gainCard)
        layout.addView(lossCard)
        layout.addView(progressCard)
        layout.addView(progressBar)
        layout.addView(historyCard)
        layout.addView(evolutionCard)
        layout.addView(errorMessage)

        scrollView.addView(layout)

        return scrollView
    }

    // =========================================
    // OBSERVATION DU VIEWMODEL
    // =========================================

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        observeWeightState()
    }

    private fun observeWeightState() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {

                viewModel.uiState.collect { state ->

                    currentCard.text =
                        "Poids actuel\n\n" +
                        formatWeight(state.currentWeight)

                    targetCard.text =
                        "Objectif de poids\n\n" +
                        formatWeight(state.targetWeight)

                    gainCard.text =
                        "Prise de poids\n\n" +
                        formatWeight(state.gainedWeight)

                    lossCard.text =
                        "Perte de poids\n\n" +
                        formatWeight(state.lostWeight)

                    progressCard.text =
                        "Progression\n\n" +
                        String.format(
                            java.util.Locale.FRANCE,
                            "%.1f %%",
                            state.progressPercentage
                        )

                    progressBar.progress =
                        state.progressPercentage
                            .coerceIn(0f, 100f)
                            .toInt()

                    errorMessage.text =
                        state.error ?: ""

                    errorMessage.visibility =
                        if (state.error != null) {
                            View.VISIBLE
                        } else {
                            View.GONE
                        }
                }
            }
        }
    }

    // =========================================
    // CREATION DES CARTES
    // =========================================

    private fun createDetail(
        title: String,
        description: String
    ): TextView {

        return TextView(requireContext()).apply {

            text = "$title\n\n$description"

            textSize = 16f

            setTextColor(
                Color.rgb(40, 50, 70)
            )

            setPadding(24, 28, 24, 28)

            background = GradientDrawable().apply {

                setColor(Color.WHITE)

                cornerRadius = 22f

                setStroke(
                    1,
                    Color.rgb(225, 232, 240)
                )
            }

            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {

                setMargins(0, 8, 0, 8)
            }
        }
    }

    // =========================================
    // FORMATAGE DU POIDS
    // =========================================

    private fun formatWeight(value: Float?): String {

        if (value == null) {
            return "Aucune donnée disponible"
        }

        return String.format(
            java.util.Locale.FRANCE,
            "%.1f kg",
            value
        )
    }
}

    
