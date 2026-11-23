package com.example.health2.core.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Centralise les Dispatchers de l'application.
 */
@Singleton
class AppDispatchers @Inject constructor() {

    val io: CoroutineDispatcher
        get() = Dispatchers.IO

    val default: CoroutineDispatcher
        get() = Dispatchers.Default

    val main: CoroutineDispatcher
        get() = Dispatchers.Main
}
