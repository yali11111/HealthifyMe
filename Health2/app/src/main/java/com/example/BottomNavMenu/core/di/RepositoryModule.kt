package com.example.BottomNavMenu.core.di

import com.example.BottomNavMenu.domain.repository.UserRepository
import com.example.BottomNavMenu.domain.repository.StepsRepository
import com.example.BottomNavMenu.domain.repository.WeightRepository
import com.example.BottomNavMenu.domain.repository.SleepRepository
import com.example.BottomNavMenu.domain.repository.CaloriesRepository
import com.example.BottomNavMenu.data.repository.UserRepositoryImpl
import com.example.BottomNavMenu.data.repository.StepsRepositoryImpl
import com.example.BottomNavMenu.data.repository.WeightRepositoryImpl
import com.example.BottomNavMenu.data.repository.SleepRepositoryImpl
import com.example.BottomNavMenu.data.repository.CaloriesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Liaison DOMAIN / DATA.
 *
 * Les implementations doivent exister avant compilation.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        implementation: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindStepsRepository(
        implementation: StepsRepositoryImpl
    ): StepsRepository

    @Binds
    @Singleton
    abstract fun bindWeightRepository(
        implementation: WeightRepositoryImpl
    ): WeightRepository

    @Binds
    @Singleton
    abstract fun bindSleepRepository(
        implementation: SleepRepositoryImpl
    ): SleepRepository

    @Binds
    @Singleton
    abstract fun bindCaloriesRepository(
        implementation: CaloriesRepositoryImpl
    ): CaloriesRepository
}
