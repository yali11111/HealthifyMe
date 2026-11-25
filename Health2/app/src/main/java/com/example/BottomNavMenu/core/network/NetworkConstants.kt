package com.example.BottomNavMenu.core.network

object NetworkConstants {

    const val CONNECT_TIMEOUT = 30L

    const val READ_TIMEOUT = 30L

    const val WRITE_TIMEOUT = 30L

    const val CALL_TIMEOUT = 60L

    const val MAX_RETRIES = 2

    /**
     * A remplacer par l'adresse HTTPS
     * de votre véritable serveur.
     */
    const val BASE_URL = "https://api.example.com/"

    const val HEADER_AUTHORIZATION = "Authorization"

    const val HEADER_CONTENT_TYPE = "Content-Type"

    const val CONTENT_TYPE_JSON = "application/json"

}
