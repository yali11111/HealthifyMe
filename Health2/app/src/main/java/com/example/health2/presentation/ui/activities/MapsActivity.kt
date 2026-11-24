package com.example.health2.presentation.ui.activities

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Activité consacrée aux cartes et aux lieux
 * situés à proximité de l'utilisateur.
 *
 * Cette première version prépare l'interface.
 * L'intégration de Google Maps sera ajoutée
 * ultérieurement.
 */
class MapsActivity : Activity() {

    /**
     * Initialise l'activité Maps.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createMapsInterface()
    }

    /**
     * Construit l'interface de la carte.
     */
    private fun createMapsInterface() {

        // Conteneur principal
        val layout = LinearLayout(this).apply {

            orientation = LinearLayout.VERTICAL

            gravity = Gravity.CENTER

            setPadding(24, 24, 24, 24)

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        // Titre
        val title = TextView(this).apply {

            text = "Health2 - Cartes"

            textSize = 24f

            gravity = Gravity.CENTER

            setPadding(0, 0, 0, 24)
        }

        // Message informatif
        val message = TextView(this).apply {

            text = "Les lieux à proximité seront affichés ici."

            textSize = 16f

            gravity = Gravity.CENTER

            setPadding(0, 0, 0, 24)
        }

        // Bouton retour
        val backButton = Button(this).apply {

            text = "Retour"

            setOnClickListener {
                finish()
            }
        }

        // Ajout des composants
        layout.addView(title)

        layout.addView(message)

        layout.addView(backButton)

        // Affichage de l'interface
        setContentView(layout)
    }
}
