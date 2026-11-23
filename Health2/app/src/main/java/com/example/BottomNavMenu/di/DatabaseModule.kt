package com.example.health2.di

import android.content.Context import androidx.room.Room import com.example.health2.data.local.db.AppDatabase import com.example.health2.data.local.db.dao.UserStatsDao import dagger.Module import dagger.Provides import dagger.hilt.InstallIn import dagger.hilt.android.qualifiers.ApplicationContext import dagger.hilt.components.SingletonComponent import javax.inject.Singleton

/**

Module responsable de la configuration
de la base de données locale Health2.
Il fournit :
AppDatabase
UserStatsDao

*/ @Module @InstallIn(SingletonComponent::class) object DatabaseModule {

/**
 * Nom de la base de données.
 */
private const val DATABASE_NAME = "health2_database"

// -----------------------------------------------
// 1. FOURNITURE DE LA BASE DE DONNÉES
// -----------------------------------------------

/**
 * Crée et fournit une instance unique
 * de la base de données Room.
 *
 * @param context Contexte global Android.
 * @return Instance AppDatabase.
 */
@Provides
@Singleton
fun provideAppDatabase(
    @ApplicationContext context: Context
): AppDatabase {

    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
    )
        .build()
}

// -----------------------------------------------
// 2. FOURNITURE DU DAO
// -----------------------------------------------

/**
 * Fournit le DAO des statistiques utilisateur.
 *
 * @param database Base de données Room.
 * @return UserStatsDao.
 */
@Provides
@Singleton
fun provideUserStatsDao(
    database: AppDatabase
): UserStatsDao {

    return database.userStatsDao()
}


}