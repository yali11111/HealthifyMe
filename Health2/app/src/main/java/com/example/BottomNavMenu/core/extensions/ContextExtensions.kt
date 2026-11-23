package com.example.health2.core.extensions

import android.content.Context
import android.widget.Toast

/**
 * Affiche un message court.
 */
fun Context.showToast(message: String) {
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_SHORT
    ).show()
}

/**
 * Affiche un message long.
 */
fun Context.showLongToast(message: String) {
    Toast.makeText(
        this,
        message,
        Toast.LENGTH_LONG
    ).show()
}
