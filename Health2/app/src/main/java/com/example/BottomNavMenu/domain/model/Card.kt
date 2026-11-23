package com.example.BottomNavMenu.domain.model

/**

Modèle représentant une carte du tableau de bord.
Permet d'afficher une information de santé
sous forme de carte dans l'application. */ 

data class Card(

// Identifiant unique de la carte 
val id: String,

// Titre de la carte 
val title: String,

// Valeur principale affichée 
val value: String,

// Unité de mesure 
val unit: String? = null,

// Description complémentaire 
val description: String? = null,

// Nom de l'icône associée 
val iconName: String? = null,

// Catégorie de la carte 
val category: String? = null,

// Progression éventuelle entre 0 et 1 
val progress: Float? = null,

// Indique si la carte est visible 
val isVisible: Boolean = true ) 
