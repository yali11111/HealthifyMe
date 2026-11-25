package com.example.BottomNavMenu.core.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

/**
 * Surveillance de la connectivite Android.
 *
 * Une connexion disponible ne garantit pas
 * que le serveur API soit accessible.
 */
class NetworkMonitor(context: Context) {

    private val connectivityManager =
        context.applicationContext.getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

    val isConnected: Flow<Boolean> = callbackFlow {

        fun publishStatus() {

            val network =
                connectivityManager.activeNetwork

            val capabilities =
                connectivityManager.getNetworkCapabilities(network)

            val connected =
                capabilities?.hasCapability(
                    NetworkCapabilities.NET_CAPABILITY_INTERNET
                ) == true &&
                capabilities.hasCapability(
                    NetworkCapabilities.NET_CAPABILITY_VALIDATED
                )

            trySend(connected)
        }

        val callback = object :
            ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                publishStatus()
            }

            override fun onLost(network: Network) {
                publishStatus()
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                publishStatus()
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(
                NetworkCapabilities.NET_CAPABILITY_INTERNET
            )
            .build()

        connectivityManager.registerNetworkCallback(
            request,
            callback
        )

        publishStatus()

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }

    }.distinctUntilChanged()
}
