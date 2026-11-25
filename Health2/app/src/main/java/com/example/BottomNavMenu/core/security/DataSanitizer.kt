package com.example.BottomNavMenu.core.security

/**
 * Nettoyage des donnees textuelles.
 */
object DataSanitizer {

    fun sanitize(value: String): String {

        return value
            .replace(Regex("[\\u0000-\\u0008\\u000B\\u000C\\u000E-\\u001F\\u007F]"), "")
            .trim()
            .take(SecurityConfig.MAX_INPUT_LENGTH)
    }

    fun sanitizeForDisplay(value: String): String {

        return sanitize(value)
    }

    fun sanitizeIdentifier(value: String): String {

        return value
            .filter {
                it.isLetterOrDigit() ||
                    it == '_' ||
                    it == '-'
            }
            .take(100)
    }
}
