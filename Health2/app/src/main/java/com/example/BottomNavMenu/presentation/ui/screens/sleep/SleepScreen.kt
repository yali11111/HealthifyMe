
package com.example.health2.presentation.ui.screens.sleep

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
 * Présente :
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
        totalSleep = createCard(
            "Durée totale",
            "Chargement..."
        )

        deepSleep = createCard(
            "Sommeil profond",
            "Chargement..."
        )

        lightSleep = createCard(
            "Sommeil léger",
            "Chargement..."
        )

        remSleep = createCard(
            "Sommeil paradoxal (REM)",
            "Chargement..."
        )

        awakeDuration = createCard(
            "Durée d'éveil",
            "Chargement..."
        )

        efficiency = createCard(
            "Efficacité du sommeil",
            "Chargement..."
        )

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

    /**
     * Observation des données du sommeil.
     */
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
                        String.format(
                            Locale.FRANCE,
                            "%.1f %%",
                            state.sleepEfficiency
                        )
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
    private fun createCard(
        title: String,
        value: String
    ): TextView {

        return TextView(requireContext()).apply {

            text = buildCardText(title, value)

            textSize = 17f

            setTextColor(Color.rgb(40, 50, 70))

            gravity = Gravity.CENTER_VERTICAL

            setPadding(32, 32, 32, 32)

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
     * Convertit une durée en minutes en heures et minutes.
     */
    private fun formatDuration(minutes: Int): String {

        if (minutes < 0) {
            return "Donnée invalide"
        }

        val hours = minutes / 60
        val remainingMinutes = minutes % 60

        return when {
            hours > 0 ->
                "${hours} h ${remainingMinutes} min"

            else ->
                "${remainingMinutes} min"
        }
    }
}

    EOF

echo "[3/6] SleepScreen.kt OK"
# ==========================================
# 4. SLEEP DETAIL SCREEN
# ==========================================

cat > "$BASE/sleep/SleepDetailScreen.kt" <<'EOF' 
package com.example.health2.presentation.ui.screens.sleep

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

/**
 * Écran détaillé de l'analyse du sommeil.
 *
 * Présente :
 * - Sommeil profond.
 * - Sommeil léger.
 * - Sommeil paradoxal.
 * - Temps d'éveil.
 * - Efficacité du sommeil.
 * - Pourcentages des phases.
 */

@AndroidEntryPoint
class SleepDetailScreen : Fragment() {

    private val viewModel: SleepViewModel by viewModels()

    private lateinit var deepSleepCard: TextView
    private lateinit var lightSleepCard: TextView
    private lateinit var remSleepCard: TextView
    private lateinit var awakeCard: TextView
    private lateinit var efficiencyCard: TextView

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

            text = "Analyse du sommeil"

            textSize = 27f

            setTextColor(
                Color.rgb(35, 45, 75)
            )

            setTypeface(null, Typeface.BOLD)

            gravity = Gravity.CENTER

            setPadding(0, 0, 0, 12)
        }

        val subtitle = TextView(requireContext()).apply {

            text = "Comprendre les différentes phases nocturnes"

            textSize = 14f

            setTextColor(Color.DKGRAY)

            gravity = Gravity.CENTER

            setPadding(0, 0, 0, 28)
        }

        layout.addView(title)
        layout.addView(subtitle)

        // =====================================
        // SOMMEIL PROFOND
        // =====================================

        deepSleepCard = createDetail(
            "Sommeil profond",
            "Phase importante pour la récupération physique.",
            "0 min"
        )

        // =====================================
        // SOMMEIL LEGER
        // =====================================

        lightSleepCard = createDetail(
            "Sommeil léger",
            "Phase représentant une partie importante du cycle nocturne.",
            "0 min"
        )

        // =====================================
        // SOMMEIL REM
        // =====================================

        remSleepCard = createDetail(
            "Sommeil paradoxal (REM)",
            "Phase associée notamment aux rêves et à certains processus cognitifs.",
            "0 min"
        )

        // =====================================
        // TEMPS D'EVEIL
        // =====================================

        awakeCard = createDetail(
            "Temps d'éveil",
            "Durée totale des périodes d'éveil nocturne.",
            "0 min"
        )

        // =====================================
        // EFFICACITE
        // =====================================

        efficiencyCard = createDetail(
            "Efficacité du sommeil",
            "Rapport entre le temps réellement dormi et le temps passé au lit.",
            "Non disponible"
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

        layout.addView(deepSleepCard)
        layout.addView(lightSleepCard)
        layout.addView(remSleepCard)
        layout.addView(awakeCard)
        layout.addView(efficiencyCard)
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

        observeSleepData()
    }

    private fun observeSleepData() {

        viewLifecycleOwner.lifecycleScope.launch {

            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {

                viewModel.uiState.collect { state ->

                    deepSleepCard.text = buildString {

                        append("Sommeil profond\n\n")

                        append(
                            formatDuration(state.deepSleep)
                        )

                        append("\n")

                        append(
                            String.format(
                                java.util.Locale.FRANCE,
                                "%.1f %% du sommeil",
                                state.deepSleepPercentage
                            )
                        )
                    }

                    lightSleepCard.text = buildString {

                        append("Sommeil léger\n\n")

                        append(
                            formatDuration(state.lightSleep)
                        )
                    }

                    remSleepCard.text = buildString {

                        append("Sommeil paradoxal (REM)\n\n")

                        append(
                            formatDuration(state.remSleep)
                        )

                        append("\n")

                        append(
                            String.format(
                                java.util.Locale.FRANCE,
                                "%.1f %% du sommeil",
                                state.remSleepPercentage
                            )
                        )
                    }

                    awakeCard.text = buildString {

                        append("Temps d'éveil\n\n")

                        append(
                            formatDuration(state.awakeDuration)
                        )
                    }

                    efficiencyCard.text = buildString {

                        append("Efficacité du sommeil\n\n")

                        append(
                            String.format(
                                java.util.Locale.FRANCE,
                                "%.1f %%",
                                state.sleepEfficiency
                            )
                        )
                    }

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
        description: String,
        value: String
    ): TextView {

        return TextView(requireContext()).apply {

            text = "$title\n\n$description\n\n$value"

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

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(0, 8, 0, 8)

            layoutParams = params
        }
    }

    // =========================================
    // FORMATAGE DES DUREES
    // =========================================

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

