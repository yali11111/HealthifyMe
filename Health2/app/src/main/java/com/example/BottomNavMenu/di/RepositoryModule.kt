package com.example.health2.di

import com.example.health2.data.repository.UserStatsRepository import com.example.health2.data.repository.UserStatsRepositoryImpl import dagger.Binds import dagger.Module import dagger.hilt.InstallIn import dagger.hilt.components.SingletonComponent import javax.inject.Singleton

/**

Module Hilt responsable de l'injection
des repositories de l'application Health2.
Il associe les interfaces Repository
à leurs implémentations concrètes. */ @Module @InstallIn(SingletonComponent::class) abstract class RepositoryModule {

/**

Associe UserStatsRepository à
UserStatsRepositoryImpl.
Hilt utilisera automatiquement
cette implémentation lorsqu'une classe
demandera UserStatsRepository. */ @Binds @Singleton abstract fun bindUserStatsRepository( implementation: UserStatsRepositoryImpl ): UserStatsRepository

}