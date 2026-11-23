package com.example.health2.data.repository

import com.example.health2.data.local.db.entity.UserStatsEntity
import kotlinx.coroutines.flow.Flow

/**
 * Interface définissant les opérations
 * de gestion des statistiques utilisateur.
 */
interface UserStatsRepository {

    // Récupération des statistiques
    fun getAllStats(
        userId: String
    ): Flow<List<UserStatsEntity>>

    // Récupération de la dernière mesure
    suspend fun getLatestStats(
        userId: String
    ): UserStatsEntity?

    // Insertion d'une nouvelle mesure
    suspend fun insertStats(
        stats: UserStatsEntity
    ): Long

    // Récupération des statistiques par période
    fun getStatsBetweenDates(
        userId: String,
        startDate: Long,
        endDate: Long
    ): Flow<List<UserStatsEntity>>

    // Synchronisation avec le serveur
    suspend fun synchronizeStats(
        userId: String
    ): Result<Unit>

    // Actualisation depuis le serveur
    suspend fun refreshStats(
        userId: String
    ): Result<Unit>

    // Suppression des statistiques
    suspend fun deleteAllStats(
        userId: String
    )
}
