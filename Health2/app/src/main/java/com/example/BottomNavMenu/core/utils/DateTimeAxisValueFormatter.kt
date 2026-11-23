
package com.example.health2.core.utils

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Formateur permettant d'afficher des dates et heures
 * sur les axes des graphiques Health2.
 *
 * Les valeurs X sont interprétées comme des timestamps
 * Unix exprimés en millisecondes.
 *
 * @param pattern Format d'affichage de la date et de l'heure.
 * @param locale Locale utilisée pour le formatage.
 * @param timeZone Fuseau horaire utilisé pour afficher les dates.
 */
class DateTimeAxisValueFormatter(
    private val pattern: String = "dd/MM HH:mm",
    locale: Locale = Locale.FRANCE,
    timeZone: TimeZone = TimeZone.getDefault()
) : ValueFormatter() {

    private val dateTimeFormat: SimpleDateFormat =
        SimpleDateFormat(pattern, locale).apply {
            this.timeZone = timeZone
        }

    /**
     * Convertit un timestamp en date et heure lisibles.
     *
     * @param value Timestamp Unix en millisecondes.
     * @param axis Axe du graphique.
     * @return Date et heure formatées.
     */
    override fun getAxisLabel(
        value: Float,
        axis: AxisBase?
    ): String {

        if (!value.isFinite()) {
            return ""
        }

        return try {
            dateTimeFormat.format(Date(value.toLong()))
        } catch (_: IllegalArgumentException) {
            ""
        }
    }
}
