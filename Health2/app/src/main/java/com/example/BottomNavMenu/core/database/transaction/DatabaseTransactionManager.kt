package com.example.BottomNavMenu.core.database.transaction

import androidx.room.withTransaction
import com.example.BottomNavMenu.core.database.HealthDatabase

/**
 * Centralise les transactions Room.
 *
 * Les operations fournies doivent rester courtes.
 */
class DatabaseTransactionManager(
    private val database: HealthDatabase
) {

    suspend fun <T> execute(
        operation: suspend () -> T
    ): T {
        return database.withTransaction {
            operation()
        }
    }
}
