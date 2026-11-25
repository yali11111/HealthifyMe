package com.example.BottomNavMenu.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.BottomNavMenu.data.local.entity.StepsEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface StepsDao {

    @Query("SELECT * FROM steps ORDER BY date DESC")
    fun observeAll(): Flow<List<StepsEntity>>

    @Query("SELECT * FROM steps WHERE date = :date LIMIT 1")
    suspend fun getByDate(date: LocalDate): StepsEntity?

    @Query("SELECT * FROM steps ORDER BY date DESC LIMIT :limit")
    fun observeRecent(limit: Int): Flow<List<StepsEntity>>

    @Query("SELECT COALESCE(SUM(stepsCount), 0) FROM steps WHERE date BETWEEN :start AND :end")
    suspend fun getTotalSteps(
        start: LocalDate,
        end: LocalDate
    ): Long

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: StepsEntity)

    @Query("DELETE FROM steps WHERE id = :id")
    suspend fun delete(id: Long)
}
