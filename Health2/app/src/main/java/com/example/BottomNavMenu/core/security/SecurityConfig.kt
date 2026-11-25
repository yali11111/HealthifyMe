package com.example.BottomNavMenu.core.security

/**
 * Constantes de securite de BottomNavMenu.
 */
object SecurityConfig {

    const val HTTPS_REQUIRED = true

    const val DEBUG_LOGGING_ALLOWED = false

    const val SESSION_TIMEOUT_MINUTES = 15L

    const val MAX_LOGIN_ATTEMPTS = 5

    const val MAX_INPUT_LENGTH = 500

    const val TOKEN_STORAGE =
        "ANDROID_KEYSTORE_ENCRYPTED"

    /**
     * Ne jamais placer de mot de passe,
     * cle API privee ou secret ici.
     */
}
