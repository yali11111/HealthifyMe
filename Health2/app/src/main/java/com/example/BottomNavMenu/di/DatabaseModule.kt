package com.example.BottomNavMenu.di

import android.content.Context 
import androidx.room.Room 
import com.example.health2.core.utils.Constants 
import com.example.health2.data.local.db.AppDatabase 
import com.example.health2.data.local.db.dao.UserStatsDao 
import dagger.Module import dagger.Provides 
import dagger.hilt.InstallIn 
import dagger.hilt.android.qualifiers.ApplicationContext 
import dagger.hilt.components.SingletonComponent 
import javax.inject.Singleton

/**

Module de configuration de la base Room.
Fournit la base de données locale et les DAO. */ 
@Module @InstallIn(SingletonComponent::class) object DatabaseModule {

/**

Création de la base de données Health2. */ 
@Provides 
@Singleton 
fun provideAppDatabase( @ApplicationContext context: Context ): AppDatabase {

return Room.databaseBuilder( context, AppDatabase::class.java, Constants.DATABASE_NAME ).build() }

/**

Fournit le DAO des statistiques utilisateur. */ 
@Provides 
@Singleton 
fun provideUserStatsDao( database: AppDatabase ): UserStatsDao {

return database.userStatsDao() } } 
