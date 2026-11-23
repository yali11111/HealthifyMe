package com.example.health2.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.health2.data.local.db.entity.UserStats
import kotlinx.coroutines.flow.Flow

/**
 * DAO Room permettant de gérer les statistiques
 * de l'utilisateur dans la base de données Health2.
 *
 * Centralise les opérations de lecture, d'insertion,
 * de modification et de suppression.
 */
@Dao
interface UserStatsDao {

    /**
     * Observe les statistiques de l'utilisateur.
     *
     * Les modifications de la table sont automatiquement
     * transmises aux observateurs.
     */
    @Query("SELECT * FROM user_stats LIMIT 1")
    fun observeUserStats(): Flow<UserStats?>

    /**
     * Récupère les statistiques enregistrées.
     *
     * @return statistiques ou null si aucune donnée n'existe.
     */
    @Query("SELECT * FROM user_stats LIMIT 1")
    suspend fun getUserStats(): UserStats?

    /**
     * Insère ou remplace les statistiques.
     *
     * En cas de conflit de clé primaire,
     * l'enregistrement existant est remplacé.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserStats(userStats: UserStats)

    /**
     * Insère plusieurs statistiques.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllUserStats(
        userStats: List<UserStats>
    )

    /**
     * Met à jour les statistiques existantes.
     *
     * @return nombre de lignes modifiées.
     */
    @Update
    suspend fun updateUserStats(userStats: UserStats): Int

    /**
     * Supprime les statistiques fournies.
     *
     * @return nombre de lignes supprimées.
     */
    @Delete
    suspend fun deleteUserStats(userStats: UserStats): Int

    /**
     * Supprime toutes les statistiques.
     *
     * @return nombre de lignes supprimées.
     */
    @Query("DELETE FROM user_stats")
    suspend fun deleteAllUserStats(): Int

    /**
     * Compte le nombre d'enregistrements.
     */
    @Query("SELECT COUNT(*) FROM user_stats")
    suspend fun countUserStats(): Int

    /**
     * Vérifie si des statistiques existent.
     */
    @Query("SELECT EXISTS(SELECT 1 FROM user_stats LIMIT 1)")
    suspend fun hasUserStats(): Boolean
}

