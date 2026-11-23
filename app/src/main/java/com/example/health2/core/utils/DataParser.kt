package com.example.health2.core.utils

import java.util.Locale

/**
 * Utilitaires de conversion et de validation des données.
 *
 * Centralise les conversions utilisées dans les différentes
 * couches de l'application Health2.
 */
object DataParser {

    /**
     * Nettoie une chaîne de caractères.
     */
    fun normalizeText(value: String?): String {
        return value?.trim().orEmpty()
    }

    /**
     * Convertit une chaîne en Int.
     */
    fun parseInt(value: String?): Int? {
        return normalizeText(value).toIntOrNull()
    }

    /**
     * Convertit une chaîne en Long.
     */
    fun parseLong(value: String?): Long? {
        return normalizeText(value).toLongOrNull()
    }

    /**
     * Convertit une chaîne en Double.
     *
     * Accepte les virgules comme séparateurs décimaux.
     */
    fun parseDouble(value: String?): Double? {
        val normalized = normalizeText(value)
            .replace(',', '.')

        return normalized.toDoubleOrNull()
            ?.takeIf { it.isFinite() }
    }

    /**
     * Convertit une chaîne en Float.
     */
    fun parseFloat(value: String?): Float? {
        val normalized = normalizeText(value)
            .replace(',', '.')

        return normalized.toFloatOrNull()
            ?.takeIf { it.isFinite() }
    }

    /**
     * Convertit une chaîne en Boolean.
     *
     * Valeurs acceptées :
     * true, false, oui, non, 1, 0.
     */
    fun parseBoolean(value: String?): Boolean? {
        return when (normalizeText(value).lowercase(Locale.ROOT)) {
            "true", "oui", "yes", "1" -> true
            "false", "non", "no", "0" -> false
            else -> null
        }
    }

    /**
     * Vérifie si une chaîne est vide ou ne contient que des espaces.
     */
    fun isEmpty(value: String?): Boolean {
        return value.isNullOrBlank()
    }

    /**
     * Convertit une chaîne en liste de mots.
     */
    fun parseStringList(
        value: String?,
        separator: String = ","
    ): List<String> {
        if (separator.isEmpty()) {
            return emptyList()
        }

        return normalizeText(value)
            .split(separator)
            .map { it.trim() }
            .filter { it.isNotEmpty() }
    }

    /**
     * Convertit une valeur numérique en texte.
     */
    fun formatDouble(
        value: Double,
        decimalPlaces: Int = 2
    ): String {
        require(value.isFinite()) {
            "La valeur doit être un nombre fini."
        }

        require(decimalPlaces >= 0) {
            "Le nombre de décimales ne peut pas être négatif."
        }

        return String.format(
            Locale.ROOT,
            "%.${decimalPlaces}f",
            value
        )
    }

    /**
     * Vérifie si une valeur numérique appartient à un intervalle.
     */
    fun isInRange(
        value: Double,
        minimum: Double,
        maximum: Double
    ): Boolean {
        require(minimum <= maximum) {
            "La valeur minimale doit être inférieure à la valeur maximale."
        }

        return value.isFinite() &&
            value >= minimum &&
            value <= maximum
    }
}
