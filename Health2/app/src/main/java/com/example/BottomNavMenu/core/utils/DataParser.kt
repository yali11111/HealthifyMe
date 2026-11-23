package com.example.health2.core.utils

import java.text.ParseException import java.text.SimpleDateFormat import java.util.Date import java.util.Locale import java.util.TimeZone

/**

Utilitaire de conversion et de traitement des données
de santé de l'application Health2.
Cette classe permet de convertir les données brutes
en valeurs numériques ou temporelles exploitables. */ object DataParser {

private val locale = Locale.FRANCE

// -------------------------------------------------- // CONVERSION NUMÉRIQUE // --------------------------------------------------

/**

Convertit une chaîne en Float.
Exemple :
"72.5" -> 72.5f */ fun toFloat(value: String?): Float? {

if (value.isNullOrBlank()) { return null }

return value.trim() .replace(",", ".") .toFloatOrNull() ?.takeIf { it.isFinite() } }

/**

Convertit une chaîne en Int. */ fun toInt(value: String?): Int? {

if (value.isNullOrBlank()) { return null }

return value.trim().toIntOrNull() }

/**

Convertit une valeur en Double. */ fun toDouble(value: String?): Double? {

if (value.isNullOrBlank()) { return null }

return value.trim() .replace(",", ".") .toDoubleOrNull() ?.takeIf { it.isFinite() } }

// -------------------------------------------------- // VALIDATION DES DONNÉES // --------------------------------------------------

/**

Vérifie si une valeur numérique est valide. */ fun isValidNumber(value: Float?): Boolean {

return value != null && value.isFinite() }

/**

Vérifie si une fréquence cardiaque est plausible.
Ces limites servent uniquement à détecter
des valeurs aberrantes dans les données. */ fun isValidHeartRate(value: Int?): Boolean {

return value != null && value in Constants.MIN_HEART_RATE..Constants.MAX_HEART_RATE }

/**

Vérifie si un poids est valide. */ fun isValidWeight(value: Float?): Boolean {

return value != null && value.isFinite() && value in Constants.MIN_WEIGHT..Constants.MAX_WEIGHT }

// -------------------------------------------------- // CONVERSION DES DATES // --------------------------------------------------

/**

Convertit une date au format texte en timestamp.
Exemple :
"23/11/2026" -> timestamp Unix en millisecondes. */ fun dateToTimestamp( date: String?, pattern: String = Constants.DATE_FORMAT, timeZone: TimeZone = TimeZone.getDefault() ): Long? {

if (date.isNullOrBlank()) { return null }

return try {

 val formatter = SimpleDateFormat(pattern, locale).apply {
     this.timeZone = timeZone
     isLenient = false
 }

 val parsedDate = formatter.parse(date.trim())

 parsedDate?.time


} catch (: ParseException) { null } catch (: IllegalArgumentException) { null } }

/**

Convertit un timestamp en date formatée. */ fun timestampToDate( timestamp: Long, pattern: String = Constants.DATE_FORMAT, timeZone: TimeZone = TimeZone.getDefault() ): String {

val formatter = SimpleDateFormat(pattern, locale).apply { this.timeZone = timeZone }

return formatter.format(Date(timestamp)) }

/**

Convertit un timestamp en date et heure. */ fun timestampToDateTime( timestamp: Long, timeZone: TimeZone = TimeZone.getDefault() ): String {

return timestampToDate( timestamp = timestamp, pattern = Constants.DATE_TIME_FORMAT, timeZone = timeZone ) }

// -------------------------------------------------- // CONVERSION DES UNITÉS // --------------------------------------------------

/**

Convertit des mètres en kilomètres. */ fun metersToKilometers(meters: Float): Float? {

if (!meters.isFinite() || meters < 0f) { return null }

return meters / 1000f }

/**

Convertit des centimètres en mètres. */ fun centimetersToMeters(centimeters: Float): Float? {

if (!centimeters.isFinite() || centimeters < 0f) { return null }

return centimeters / 100f }

/**

Convertit des secondes en heures. */ fun secondsToHours(seconds: Long): Float? {

if (seconds < 0L) { return null }

return seconds.toFloat() / 3600f }

/**

Convertit des kilogrammes en grammes. */ fun kilogramsToGrams(kilograms: Float): Float? {

if (!kilograms.isFinite() || kilograms < 0f) { return null }

return kilograms * 1000f }

// -------------------------------------------------- // TRAITEMENT DES DONNÉES DE GRAPHIQUES // --------------------------------------------------

/**

Convertit une liste de chaînes en liste de Float.
Les valeurs invalides sont ignorées. */ fun parseFloatList(values: List<String?>): List<Float> {

return values.mapNotNull { toFloat(it) } }

/**

Convertit une liste de chaînes en liste de Double. */ fun parseDoubleList(values: List<String?>): List<Double> {

return values.mapNotNull { toDouble(it) } }

/**

Calcule la moyenne d'une liste de valeurs. */ fun average(values: List<Float>): Float? {

val validValues = values.filter { it.isFinite() }

return if (validValues.isNotEmpty()) { validValues.average().toFloat() } else { null } }

/**

Retourne la valeur minimale d'une liste. */ fun minimum(values: List<Float>): Float? {

return values.filter { it.isFinite() }.minOrNull() }

/**

Retourne la valeur maximale d'une liste. */ fun maximum(values: List<Float>): Float? {

return values.filter { it.isFinite() }.maxOrNull() } }
