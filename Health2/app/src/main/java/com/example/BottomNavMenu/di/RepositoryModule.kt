package com.example.BottomNavMenu.di

import com.example.health2.data.repository.ExerciseRepository 
import com.example.health2.data.repository.ExerciseRepositoryImpl 
import com.example.health2.data.repository.SuggestionRepository 
import com.example.health2.data.repository.SuggestionRepositoryImpl 
import com.example.health2.data.repository.UserStatsRepository 
import com.example.health2.data.repository.UserStatsRepositoryImpl 
import dagger.Binds 
import dagger.Module 
import dagger.hilt.InstallIn 
import dagger.hilt.components.SingletonComponent 
import javax.inject.Singleton

/**

Module de liaison des repositories.
Associe les interfaces métier à leurs
implémentations concrètes. */ @Module @InstallIn(SingletonComponent::class) abstract class RepositoryModule {

/**

Repository des statistiques de santé. */ @Binds @Singleton abstract fun bindUserStatsRepository( implementation: UserStatsRepositoryImpl ): UserStatsRepository

/**

Repository des suggestions. */ 
@Binds 
@Singleton abstract 
fun bindSuggestionRepository( implementation: SuggestionRepositoryImpl ): SuggestionRepository

/**

Repository des exercices. */ 
@Binds 
@Singleton abstract 
fun bindExerciseRepository( implementation: ExerciseRepositoryImpl ): ExerciseRepository } 
