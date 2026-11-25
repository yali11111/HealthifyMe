package com.example.BottomNavMenu.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.BottomNavMenu.data.local.entity.CaloriesEntity
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface CaloriesDao {

    @Query("SELECT * FROM calories ORDER BY date DESC")
    fun observeAll(): Flow<List<CaloriesEntity>>

    @Query("SELECT * FROM calories WHERE date = :date LIMIT 1")
    suspend fun getByDate(date: LocalDate): CaloriesEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: CaloriesEntity)

    @Query("DELETE FROM calories WHERE id = :id")
    suspend fun delete(id: Long)
}
