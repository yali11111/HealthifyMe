package com.example.BottomNavMenu.di

import com.example.health2.core.dispatcher.AppDispatchers 
import dagger.Module import dagger.Provides 
import dagger.hilt.InstallIn 
import dagger.hilt.components.SingletonComponent 
import kotlinx.coroutines.CoroutineDispatcher 
import javax.inject.Named 
import javax.inject.Singleton

/**

Module principal d'injection des dépendances.
Centralise les ressources générales utilisées
dans toute l'application Health2. */ 
@Module 
@InstallIn(SingletonComponent::class) object AppModule {

/**

Dispatcher pour les opérations d'entrée/sortie. */ 
@Provides 
@Singleton 
@Named("io") 
fun provideIoDispatcher( dispatchers: AppDispatchers ): CoroutineDispatcher { return dispatchers.io }

/**

Dispatcher pour les calculs intensifs. */ 
@Provides 
@Singleton 
@Named("default") 
fun provideDefaultDispatcher( dispatchers: AppDispatchers ): CoroutineDispatcher { return dispatchers.default }

/**

Dispatcher pour les opérations UI. */ 
@Provides 
@Singleton 
@Named("main") 
fun provideMainDispatcher( dispatchers: AppDispatchers ): CoroutineDispatcher { return dispatchers.main } } 
