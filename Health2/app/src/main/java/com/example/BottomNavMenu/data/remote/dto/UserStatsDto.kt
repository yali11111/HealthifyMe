package com.example.health2.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * Objet de transfert des statistiques de santé.
 *
 * Ce DTO représente les données reçues du serveur.
 */
data class UserStatsDto(

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("userId")
    val userId: String,

    @SerializedName("timestamp")
    val timestamp: Long,

    // Données anthropométriques

    @SerializedName("weight")
    val weight: Float? = null,

    @SerializedName("height")
    val height: Float? = null,

    @SerializedName("bmi")
    val bmi: Float? = null,

    // Données cardiaques

    @SerializedName("heartRate")
    val heartRate: Int? = null,

    @SerializedName("systolicPressure")
    val systolicPressure: Int? = null,

    @SerializedName("diastolicPressure")
    val diastolicPressure: Int? = null,

    // Données respiratoires

    @SerializedName("oxygenSaturation")
    val oxygenSaturation: Float? = null,

    @SerializedName("respiratoryRate")
    val respiratoryRate: Int? = null,

    // Température

    @SerializedName("bodyTemperature")
    val bodyTemperature: Float? = null,

    // Activité physique

    @SerializedName("steps")
    val steps: Int? = null,

    @SerializedName("distance")
    val distance: Float? = null,

    @SerializedName("calories")
    val calories: Float? = null,

    @SerializedName("activityDuration")
    val activityDuration: Int? = null,

    // Sommeil

    @SerializedName("sleepDuration")
    val sleepDuration: Int? = null,

    @SerializedName("deepSleepDuration")
    val deepSleepDuration: Int? = null,

    @SerializedName("lightSleepDuration")
    val lightSleepDuration: Int? = null,

    @SerializedName("remSleepDuration")
    val remSleepDuration: Int? = null
)
