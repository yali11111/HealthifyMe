package com.example.BottomNavMenu.core.database

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Callback pour les evenements de creation et ouverture.
 *
 * Ne pas y placer de donnees personnelles ou de secrets.
 */
class DatabaseCallback : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        // Initialisation non bloquante si necessaire.
    }

    override fun onOpen(db: SupportSQLiteDatabase) {
        super.onOpen(db)
        // Verification complementaire si necessaire.
    }
}
