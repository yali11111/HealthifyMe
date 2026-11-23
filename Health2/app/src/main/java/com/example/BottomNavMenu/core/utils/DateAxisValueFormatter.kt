package com.example.health2.core.utils

import com.github.mikephil.charting.components.AxisBase import com.github.mikephil.charting.formatter.ValueFormatter import java.text.SimpleDateFormat import java.util.Date import java.util.Locale import java.util.TimeZone

/**

Formateur permettant d'afficher des dates
sur les axes des graphiques Health2.
Les valeurs X sont interprétées comme des timestamps
Unix exprimés en millisecondes.
Exemple :
1795478400000 -> 23/11/2026
@param pattern Format d'affichage de la date.
@param locale Locale utilisée pour le formatage.
@param timeZone Fuseau horaire utilisé. */ class DateAxisValueFormatter( private val pattern: String = Constants.DATE_FORMAT, locale: Locale = Locale.FRANCE, timeZone: TimeZone = TimeZone.getDefault() ) : ValueFormatter() {

/**

Formateur de date. */ private val dateFormat: SimpleDateFormat = SimpleDateFormat(pattern, locale).apply {
 this.timeZone = timeZone


}

/**

Convertit un timestamp en date lisible.
@param value Timestamp Unix en millisecondes.
@param axis Axe du graphique.
@return Date formatée. */ override fun getAxisLabel( value: Float, axis: AxisBase? ): String {

// Vérification des valeurs numériques. if (!value.isFinite()) { return "" }

return try {

 val date = Date(value.toLong())

 dateFormat.format(date)


} catch (_: IllegalArgumentException) {

 ""


} } }
