package com.example.BottomNavMenu.core.security

/**
 * Validation des donnees utilisateur.
 */
object InputValidator {

    fun isValidEmail(email: String): Boolean {

        if (email.isBlank() || email.length > 254) {
            return false
        }

        return android.util.Patterns.EMAIL_ADDRESS
            .matcher(email)
            .matches()
    }

    fun isValidPassword(password: String): Boolean {

        return password.length >= 12 &&
            password.length <= 128 &&
            password.any { it.isUpperCase() } &&
            password.any { it.isLowerCase() } &&
            password.any { it.isDigit() }
    }

    fun isValidWeight(weight: Double): Boolean {

        return weight.isFinite() &&
            weight in 1.0..500.0
    }

    fun isValidHeight(height: Double): Boolean {

        return height.isFinite() &&
            height in 30.0..300.0
    }

    fun isValidAge(age: Int): Boolean {

        return age in 1..120
    }

    fun isValidSteps(steps: Int): Boolean {

        return steps in 0..200000
    }

    fun isValidInput(value: String): Boolean {

        return value.isNotBlank() &&
            value.length <= SecurityConfig.MAX_INPUT_LENGTH
    }
}
