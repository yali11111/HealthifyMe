package com.example.health2.data.remote.api

import android.net.Uri

/**
 * Utilitaire de construction des URL de téléchargement.
 */
object DownloadURL {

    /**
     * Construit une URL à partir d'un chemin serveur.
     */
    fun build(
        baseUrl: String,
        path: String
    ): String {

        return baseUrl.trimEnd('/') +
                "/" +
                path.trimStart('/')
    }

    /**
     * Ajoute des paramètres à une URL.
     */
    fun withParameters(
        url: String,
        parameters: Map<String, String>
    ): String {

        val builder = Uri.parse(url).buildUpon()

        parameters.forEach { (key, value) ->
            builder.appendQueryParameter(key, value)
        }

        return builder.build().toString()
    }

    /**
     * Vérifie si une URL est valide.
     */
    fun isValid(url: String): Boolean {

        return try {
            val uri = Uri.parse(url)

            (uri.scheme == "https" || uri.scheme == "http") &&
                    !uri.host.isNullOrBlank()

        } catch (_: Exception) {
            false
        }
    }
}
