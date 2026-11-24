package com.example.health2.presentation.ui.activities

import android.app.Activity 
import android.content.Intent 
import android.os.Bundle 
import android.view.Gravity 
import android.view.ViewGroup 
import android.widget.Button 
import android.widget.LinearLayout 
import android.widget.TextView

/**

Activité principale de l'application Health2.
Elle constitue le point d'entrée de l'interface
utilisateur et permet d'accéder aux différentes
fonctionnalités de l'application. */ 

class MainActivity : Activity() {

/**

Initialise l'interface principale. */ 
override fun onCreate(savedInstanceState: Bundle?) { super.onCreate(savedInstanceState)

createMainInterface() }

/**

Construit l'interface principale. */ 
private fun createMainInterface() {

// Conteneur principal 

val layout = LinearLayout(this).apply {

 orientation = LinearLayout.VERTICAL

 gravity = Gravity.CENTER

 setPadding(32, 32, 32, 32)

 layoutParams = ViewGroup.LayoutParams(
     ViewGroup.LayoutParams.MATCH_PARENT,
     ViewGroup.LayoutParams.MATCH_PARENT
 )


}

// Titre de l'application 

val title = TextView(this).apply {

 text = "Bienvenue sur Health2"

 textSize = 26f

 gravity = Gravity.CENTER

 setPadding(0, 0, 0, 24)


}

// Description 

val description = TextView(this).apply {

 text = "Suivez votre santé et votre activité physique."

 textSize = 16f

 gravity = Gravity.CENTER

 setPadding(0, 0, 0, 32)


}

// Bouton d'accès aux cartes 

val mapsButton = Button(this).apply {

 text = "Découvrir les lieux à proximité"

 setOnClickListener {

     val intent = Intent(
         this@MainActivity,
         MapsActivity::class.java
     )

     startActivity(intent)
 }


}

// Ajout des composants 

layout.addView(title)

layout.addView(description)

layout.addView(mapsButton)

// Affichage de l'interface 

setContentView(layout) } } 
