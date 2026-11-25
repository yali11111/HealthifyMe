package com.example.BottomNavMenu.core.security

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.nio.ByteBuffer
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

/**
 * Stockage de petites valeurs sensibles.
 *
 * Utilise Android Keystore avec AES/GCM.
 *
 * Ne pas utiliser pour stocker de gros fichiers.
 */
class SecurePreferences(context: Context) {

    companion object {
        private const val KEYSTORE = "AndroidKeyStore"
        private const val KEY_ALIAS = "BottomNavMenu_secure_preferences"
        private const val TRANSFORMATION = "AES/GCM/NoPadding"
        private const val PREFS_NAME = "BottomNavMenu_secure_storage"
        private const val IV_LENGTH = 12
        private const val TAG_LENGTH = 128
    }

    private val preferences = context.applicationContext
        .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    private fun getSecretKey(): SecretKey {

        val keyStore = KeyStore.getInstance(KEYSTORE)
        keyStore.load(null)

        val existingKey = keyStore.getKey(KEY_ALIAS, null)

        if (existingKey is SecretKey) {
            return existingKey
        }

        val generator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            KEYSTORE
        )

        val specification = KeyGenParameterSpec.Builder(
            KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or
                KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setEncryptionPaddings(
                KeyProperties.ENCRYPTION_PADDING_NONE
            )
            .setRandomizedEncryptionRequired(true)
            .setKeySize(256)
            .build()

        generator.init(specification)

        return generator.generateKey()
    }

    @Synchronized
    fun putString(key: String, value: String) {

        require(key.isNotBlank()) {
            "Cle invalide."
        }

        try {

            val cipher = Cipher.getInstance(TRANSFORMATION)

            cipher.init(
                Cipher.ENCRYPT_MODE,
                getSecretKey()
            )

            val encrypted = cipher.doFinal(
                value.toByteArray(Charsets.UTF_8)
            )

            val iv = cipher.iv

            val combined = ByteBuffer.allocate(
                iv.size + encrypted.size
            )
                .put(iv)
                .put(encrypted)
                .array()

            preferences.edit()
                .putString(
                    key,
                    Base64.encodeToString(
                        combined,
                        Base64.NO_WRAP
                    )
                )
                .apply()

        } catch (exception: Exception) {

            throw IllegalStateException(
                "Impossible de securiser la donnee.",
                exception
            )
        }
    }

    @Synchronized
    fun getString(key: String): String? {

        require(key.isNotBlank()) {
            "Cle invalide."
        }

        val stored = preferences.getString(key, null)
            ?: return null

        return try {

            val combined = Base64.decode(
                stored,
                Base64.NO_WRAP
            )

            require(combined.size > IV_LENGTH)

            val buffer = ByteBuffer.wrap(combined)

            val iv = ByteArray(IV_LENGTH)
            buffer.get(iv)

            val encrypted = ByteArray(buffer.remaining())
            buffer.get(encrypted)

            val cipher = Cipher.getInstance(TRANSFORMATION)

            cipher.init(
                Cipher.DECRYPT_MODE,
                getSecretKey(),
                GCMParameterSpec(TAG_LENGTH, iv)
            )

            String(
                cipher.doFinal(encrypted),
                Charsets.UTF_8
            )

        } catch (exception: Exception) {

            // Une donnee corrompue n'est jamais retournee.
            preferences.edit().remove(key).apply()

            null
        }
    }

    fun remove(key: String) {
        preferences.edit().remove(key).apply()
    }

    fun clear() {
        preferences.edit().clear().apply()
    }
}
