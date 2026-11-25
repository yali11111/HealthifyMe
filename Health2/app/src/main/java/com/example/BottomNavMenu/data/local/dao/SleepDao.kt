package com.example.BottomNavMenu.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.BottomNavMenu.data.local.entity.SleepEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface SleepDao {

    @Query("SELECT * FROM sleeps ORDER BY date DESC")
    fun observeAll(): Flow<List<SleepEntity>>

    @Query("SELECT * FROM sleeps WHERE date = :date LIMIT 1")
    suspend fun getByDate(date: LocalDate): SleepEntity?

    @Query("SELECT * FROM sleeps ORDER BY date DESC LIMIT :limit")
    fun observeRecent(limit: Int): Flow<List<SleepEntity>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: SleepEntity)

    @Query("DELETE FROM sleeps WHERE id = :id")
    suspend fun delete(id: Long)
}
