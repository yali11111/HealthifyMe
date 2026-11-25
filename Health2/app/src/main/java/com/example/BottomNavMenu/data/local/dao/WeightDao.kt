package com.example.BottomNavMenu.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.BottomNavMenu.data.local.entity.WeightEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeightDao {

    @Query("SELECT * FROM weights ORDER BY measuredAt DESC")
    fun observeAll(): Flow<List<WeightEntity>>

    @Query("SELECT * FROM weights ORDER BY measuredAt DESC LIMIT 1")
    fun observeLatest(): Flow<WeightEntity?>

    @Query("SELECT * FROM weights ORDER BY measuredAt DESC LIMIT 1")
    suspend fun getLatest(): WeightEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(item: WeightEntity)

    @Query("DELETE FROM weights WHERE id = :id")
    suspend fun delete(id: Long)
}
