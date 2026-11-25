package com.example.BottomNavMenu.core.database

import android.content.Context
import androidx.room.Room

/**
 * Construction centralisee de la base Room.
 *
 * La base est singleton dans le processus.
 * L'injection Hilt sera configuree dans une partie DI dediee.
 */
object DatabaseFactory {

    @Volatile
    private var INSTANCE: HealthDatabase? = null

    fun create(context: Context): HealthDatabase {

        return INSTANCE ?: synchronized(this) {

            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                HealthDatabase::class.java,
                "BottomNavMenu_database"
            )
                .addMigrations(*DatabaseMigrations.ALL)
                .addCallback(DatabaseCallback())
                .build()
                .also { INSTANCE = it }
        }
    }
}
