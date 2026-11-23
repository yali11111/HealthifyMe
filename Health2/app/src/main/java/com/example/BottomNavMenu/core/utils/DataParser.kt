package com.example.health2.core.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

/**
 * Utilitaires de conversion des données.
 */
object DataParser {

    /**
     * Convertit une chaîne en Float.
     */
    fun toFloat(value: String?): Float? {

        if (value.isNullOrBlank()) {
            return null
        }

        return value.trim()
            .replace(',', '.')
            .toFloatOrNull()
            ?.takeIf { it.isFinite() }
    }

    /**
     * Convertit une chaîne en Int.
     */
    fun toInt(value: String?): Int? {

        if (value.isNullOrBlank()) {
            return null
        }

        return value.trim().toIntOrNull()
    }

    /**
     * Convertit une chaîne en Long.
     */
    fun toLong(value: String?): Long? {

        if (value.isNullOrBlank()) {
            return null
        }

        return value.trim().toLongOrNull()
    }

    /**
     * Convertit une date en timestamp.
     */
    fun parseDate(
        value: String?,
        pattern: String = "dd/MM/yyyy"
    ): Long? {

        if (value.isNullOrBlank()) {
            return null
        }

        return try {

            val formatter = SimpleDateFormat(
                pattern,
                Locale.FRANCE
            ).apply {
                isLenient = false
                timeZone = TimeZone.getDefault()
            }

            formatter.parse(value)?.time

        } catch (_: Exception) {
            null
        }
    }

    /**
     * Convertit un timestamp en chaîne.
     */
    fun formatDate(
        timestamp: Long,
        pattern: String = "dd/MM/yyyy"
    ): String {

        val formatter = SimpleDateFormat(
            pattern,
            Locale.FRANCE
        )

        return formatter.format(timestamp)
    }
}
