package com.example.health2.core.utils

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

/**
 * Formateur de dates pour les axes des graphiques.
 *
 * Les valeurs X représentent des timestamps
 * Unix exprimés en millisecondes.
 */
class DateAxisValueFormatter(
    private val pattern: String = "dd/MM",
    locale: Locale = Locale.FRANCE,
    timeZone: TimeZone = TimeZone.getDefault()
) : ValueFormatter() {

    private val dateFormat = SimpleDateFormat(
        pattern,
        locale
    ).apply {
        this.timeZone = timeZone
    }

    override fun getAxisLabel(
        value: Float,
        axis: AxisBase?
    ): String {

        if (!value.isFinite()) {
            return ""
        }

        return try {
            dateFormat.format(Date(value.toLong()))
        } catch (_: IllegalArgumentException) {
            ""
        }
    }
}
