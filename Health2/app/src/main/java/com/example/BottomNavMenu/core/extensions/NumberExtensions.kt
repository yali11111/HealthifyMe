package com.example.health2.core.extensions

import java.util.Locale

/**
 * Convertit des mètres en kilomètres.
 */
fun Float.toKilometers(): Float? {

    if (!isFinite() || this < 0f) {
        return null
    }

    return this / 1000f
}

/**
 * Convertit des centimètres en mètres.
 */
fun Float.toMeters(): Float? {

    if (!isFinite() || this < 0f) {
        return null
    }

    return this / 100f
}

/**
 * Convertit des secondes en heures.
 */
fun Long.toHours(): Float? {

    if (this < 0L) {
        return null
    }

    return this.toFloat() / 3600f
}

/**
 * Formate un nombre avec deux décimales.
 */
fun Float.toFormattedNumber(): String {

    if (!isFinite()) {
        return ""
    }

    return String.format(
        Locale.FRANCE,
        "%.2f",
        this
    )
}

/**
 * Vérifie si une valeur est positive.
 */
fun Float.isPositive(): Boolean {
    return isFinite() && this > 0f
}

/**
 * Calcule un pourcentage.
 */
fun Float.toPercentage(total: Float): Float? {

    if (
        !isFinite() ||
        !total.isFinite() ||
        total <= 0f
    ) {
        return null
    }

    return (this / total) * 100f
}

/**
 * Calcule l'indice de masse corporelle.
 *
 * weight : kilogrammes
 * heightCm : centimètres
 */
fun calculateBMI(
    weight: Float,
    heightCm: Float
): Float? {

    if (
        !weight.isFinite() ||
        !heightCm.isFinite() ||
        weight <= 0f ||
        heightCm <= 0f
    ) {
        return null
    }

    val heightMeters = heightCm / 100f

    return weight / (heightMeters * heightMeters)
}
