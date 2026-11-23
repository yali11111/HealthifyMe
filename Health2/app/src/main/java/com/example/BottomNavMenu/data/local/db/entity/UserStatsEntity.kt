package com.example.health2.data.local.db.entity

import androidx.room.Entity import androidx.room.Index import androidx.room.PrimaryKey

/**

Entité Room représentant les statistiques de santé
d'un utilisateur dans l'application Health2.
Chaque enregistrement correspond à une mesure
effectuée à une date précise.
Les timestamps sont exprimés en millisecondes Unix. */ @Entity( tableName = "user_stats", indices = [ Index(value = ["userId"]), Index(value = ["userId", "timestamp"]), Index(value = ["timestamp"]), Index(value = ["remoteId"], unique = true) ] ) data class UserStatsEntity(

// ========================================= // IDENTIFICATION // =========================================

@PrimaryKey(autoGenerate = true) val id: Long = 0,

val userId: String,

val timestamp: Long = System.currentTimeMillis(),

// ========================================= // DONNÉES ANTHROPOMÉTRIQUES // =========================================

val weight: Float? = null, // Poids en kilogrammes

val height: Float? = null, // Taille en centimètres

val bmi: Float? = null, // Indice de masse corporelle

// ========================================= // DONNÉES CARDIAQUES // =========================================

val heartRate: Int? = null, // Fréquence cardiaque (BPM)

val systolicPressure: Int? = null, // Pression systolique (mmHg)

val diastolicPressure: Int? = null, // Pression diastolique (mmHg)

// ========================================= // DONNÉES RESPIRATOIRES // =========================================

val oxygenSaturation: Float? = null, // Saturation en oxygène (%)

val respiratoryRate: Int? = null, // Fréquence respiratoire

// ========================================= // TEMPÉRATURE CORPORELLE // =========================================

val bodyTemperature: Float? = null, // Température en °C

// ========================================= // ACTIVITÉ PHYSIQUE // =========================================

val steps: Int? = null, // Nombre de pas

val distance: Float? = null, // Distance en mètres

val calories: Float? = null, // Calories dépensées (kcal)

val activityDuration: Int? = null, // Durée d'activité (minutes)

// ========================================= // DONNÉES DU SOMMEIL // =========================================

val sleepDuration: Int? = null, // Durée totale (minutes)

val deepSleepDuration: Int? = null, // Sommeil profond (minutes)

val lightSleepDuration: Int? = null, // Sommeil léger (minutes)

val remSleepDuration: Int? = null, // Sommeil paradoxal (minutes)

// ========================================= // SYNCHRONISATION // =========================================

val isSynced: Boolean = false, // État de synchronisation

val syncedAt: Long? = null, // Date de synchronisation

val remoteId: String? = null, // Identifiant distant

// ========================================= // GESTION DES DATES // =========================================

val createdAt: Long = System.currentTimeMillis(),

val updatedAt: Long = System.currentTimeMillis()

)