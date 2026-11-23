package com.example.health2.core.extensions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Convertit un timestamp en date formatée.
 */
fun Long.toFormattedDate(
    pattern: String = "dd/MM/yyyy",
    locale: Locale = Locale.FRANCE,
    timeZone: TimeZone = TimeZone.getDefault()
): String {

    val formatter = SimpleDateFormat(
        pattern,
        locale
    ).apply {
        this.timeZone = timeZone
    }

    return formatter.format(Date(this))
}

/**
 * Convertit un timestamp en date et heure.
 */
fun Long.toFormattedDateTime(): String {
    return toFormattedDate("dd/MM/yyyy HH:mm")
}

/**
 * Vérifie si le timestamp correspond à aujourd'hui.
 */
fun Long.isToday(): Boolean {

    val formatter = SimpleDateFormat(
        "yyyyMMdd",
        Locale.FRANCE
    )

    return formatter.format(Date(this)) ==
            formatter.format(Date())
}

/**
 * Retourne le timestamp actuel.
 */
fun currentTimestamp(): Long {
    return System.currentTimeMillis()
}
