package com.example.BottomNavMenu.domain.model

/**

Modèle métier représentant une suggestion de santé.
Les suggestions peuvent concerner :
le sommeil, l'alimentation, l'activité physique
ou le suivi général de la santé. */ 
data class SuggestionStructure(

// Identifiant unique 
val id: String,

// Titre de la suggestion 
val title: String,

// Description détaillée 
val description: String,

// Catégorie de la suggestion 
val category: String,

// Niveau de priorité 
val priority: String = "NORMAL",

// Action proposée à l'utilisateur 
val actionLabel: String? = null,

// Date de création 
val createdAt: Long = System.currentTimeMillis(),

// Indique si la suggestion a été consultée 
val isRead: Boolean = false,

// Indique si la suggestion est active 
val isActive: Boolean = true ) 
