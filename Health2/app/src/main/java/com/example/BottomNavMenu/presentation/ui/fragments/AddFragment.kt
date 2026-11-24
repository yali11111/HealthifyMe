package com.example.health2.presentation.ui.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * Fragment permettant d'ajouter
 * des mesures de santé.
 *
 * L'enregistrement dans Room sera intégré
 * ultérieurement.
 */
class AddFragment : Fragment() {

    /**
     * Crée l'interface d'ajout.
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

            setPadding(32, 32, 32, 32)
        }

        // Titre
        val title = TextView(requireContext()).apply {

            text = "Ajouter une mesure"

            textSize = 24f

            gravity = Gravity.CENTER

            setPadding(0, 0, 0, 24)
        }

        // Champ poids
        val weightInput = createInput(
            "Poids en kilogrammes"
        )

        // Champ taille
        val heightInput = createInput(
            "Taille en centimètres"
        )

        // Champ fréquence cardiaque
        val heartRateInput = createInput(
            "Fréquence cardiaque (bpm)"
        )

        // Bouton d'enregistrement
        val saveButton = Button(requireContext()).apply {

            text = "Enregistrer"

            setOnClickListener {

                // Récupération des valeurs
                val weight = weightInput.text.toString().trim()

                val height = heightInput.text.toString().trim()

                val heartRate = heartRateInput.text.toString().trim()

                // Vérification des champs vides
                if (
                    weight.isBlank() ||
                    height.isBlank() ||
                    heartRate.isBlank()
                ) {

                    Toast.makeText(
                        requireContext(),
                        "Veuillez remplir tous les champs.",
                        Toast.LENGTH_SHORT
                    ).show()

                    return@setOnClickListener
                }

                // Conversion des valeurs
                val validWeight = weight.toFloatOrNull()

                val validHeight = height.toFloatOrNull()

                val validHeartRate = heartRate.toIntOrNull()

                // Vérification des valeurs numériques
                if (
                    validWeight == null ||
                    !validWeight.isFinite() ||
                    validWeight <= 0f ||

                    validHeight == null ||
                    !validHeight.isFinite() ||
                    validHeight <= 0f ||

                    validHeartRate == null ||
                    validHeartRate <= 0
                ) {

                    Toast.makeText(
                        requireContext(),
                        "Veuillez saisir des valeurs numériques valides.",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {

                    // Les données sont valides
                    Toast.makeText(
                        requireContext(),
                        "Mesures vérifiées. Enregistrement Room à intégrer.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }

        // Ajout des composants
        layout.addView(title)

        layout.addView(weightInput)

        layout.addView(heightInput)

        layout.addView(heartRateInput)

        layout.addView(saveButton)

        return layout
    }

    /**
     * Crée un champ de saisie.
     *
     * @param hintText Texte indicatif du champ.
     * @return Champ EditText configuré.
     */
    private fun createInput(
        hintText: String
    ): EditText {

        return EditText(requireContext()).apply {

            hint = hintText

            textSize = 16f

            singleLine = true

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )

            params.setMargins(0, 8, 0, 8)

            layoutParams = params
        }
    }
}
