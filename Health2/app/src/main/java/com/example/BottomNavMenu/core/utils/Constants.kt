package com.example.health2.core.utils

/**

Constantes globales de l'application Health2.
Ce fichier centralise les valeurs utilisées dans les différents
modules afin d'éviter les répétitions dans le code. */ object Constants {

// -------------------------------------------------- // APPLICATION // --------------------------------------------------

const val APP_NAME = "Health2"

const val APP_VERSION = "1.0.0"

// -------------------------------------------------- // FORMATS DE DATES // --------------------------------------------------

const val DATE_FORMAT = "dd/MM/yyyy"

const val TIME_FORMAT = "HH:mm"

const val DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm"

const val SHORT_DATE_FORMAT = "dd/MM"

const val CHART_DATE_FORMAT = "dd/MM"

const val CHART_DATE_TIME_FORMAT = "dd/MM HH:mm"

// -------------------------------------------------- // UNITÉS DE MESURE // --------------------------------------------------

const val UNIT_HEART_RATE = "bpm"

const val UNIT_STEPS = "pas"

const val UNIT_WEIGHT = "kg"

const val UNIT_HEIGHT = "cm"

const val UNIT_DISTANCE = "km"

const val UNIT_CALORIES = "kcal"

const val UNIT_BLOOD_PRESSURE = "mmHg"

const val UNIT_SLEEP = "h"

const val UNIT_PERCENTAGE = "%"

// -------------------------------------------------- // PARAMÈTRES TEMPORELS // --------------------------------------------------

const val ONE_SECOND = 1000L

const val ONE_MINUTE = 60 * ONE_SECOND

const val ONE_HOUR = 60 * ONE_MINUTE

const val ONE_DAY = 24 * ONE_HOUR

const val ONE_WEEK = 7 * ONE_DAY

// -------------------------------------------------- // PARAMÈTRES DES GRAPHIQUES // --------------------------------------------------

const val CHART_LINE_WIDTH = 2f

const val CHART_CIRCLE_RADIUS = 3f

const val CHART_TEXT_SIZE = 10f

const val CHART_AXIS_TEXT_SIZE = 11f

const val CHART_ANIMATION_DURATION = 500

const val CHART_MIN_ZOOM = 1f

const val CHART_MAX_VISIBLE_VALUES = 7

// -------------------------------------------------- // INTERVALLES D'ANALYSE // --------------------------------------------------

const val PERIOD_DAY = "day"

const val PERIOD_WEEK = "week"

const val PERIOD_MONTH = "month"

const val PERIOD_YEAR = "year"

// -------------------------------------------------- // LIMITES DE DONNÉES // --------------------------------------------------

const val MAX_HEART_RATE = 250

const val MIN_HEART_RATE = 20

const val MAX_STEPS_PER_DAY = 100000

const val MAX_WEIGHT = 500f

const val MIN_WEIGHT = 1f

// -------------------------------------------------- // VALEURS PAR DÉFAUT // --------------------------------------------------

const val DEFAULT_LANGUAGE = "fr"

const val DEFAULT_COUNTRY = "FR"

const val DEFAULT_TIME_ZONE = "Europe/Paris" }
