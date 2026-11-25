package com.example.BottomNavMenu.core.di

import android.content.Context
import androidx.room.Room
import com.example.BottomNavMenu.data.local.database.HealthDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Injection de la base Room.
 *
 * HealthDatabase doit etre cree dans la PARTIE 03.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideHealthDatabase(
        @ApplicationContext context: Context
    ): HealthDatabase {

        return Room.databaseBuilder(
            context,
            HealthDatabase::class.java,
            "BottomNavMenu_database"
        )
            .build()
    }
}
