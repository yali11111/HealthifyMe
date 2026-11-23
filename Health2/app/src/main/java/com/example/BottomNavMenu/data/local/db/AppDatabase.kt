package com.example.health2.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.health2.data.local.db.dao.UserStatsDao
import com.example.health2.data.local.db.entity.UserStatsEntity

/**
 * Base de données locale Room de l'application Health2.
 *
 * Centralise la configuration de la base SQLite et expose
 * les DAO utilisés par les repositories.
 */
@Database(
    entities = [
        UserStatsEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    /**
     * DAO permettant d'accéder aux statistiques utilisateur.
     */
    abstract fun userStatsDao(): UserStatsDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Retourne l'instance unique de la base de données.
         *
         * @param context contexte Android.
         * @return instance singleton de AppDatabase.
         */
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                )
                    .build()
                    .also { database ->
                        INSTANCE = database
                    }
            }
        }

        /**
         * Nom du fichier SQLite.
         */
        private const val DATABASE_NAME = "health2_database"
    }
}

