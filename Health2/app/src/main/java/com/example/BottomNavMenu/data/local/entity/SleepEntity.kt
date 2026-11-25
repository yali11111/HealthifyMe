package com.example.BottomNavMenu.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.BottomNavMenu.core.database.converter.SleepQuality
import java.time.LocalDate

@Entity(
    tableName = "sleeps",
    indices = [
        Index(value = ["date"], unique = true)
    ]
)
data class SleepEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val date: LocalDate,

    val deepSleepMinutes: Int,

    val lightSleepMinutes: Int,

    val remSleepMinutes: Int,

    val awakeMinutes: Int = 0,

    val quality: SleepQuality? = null,

    val recordedAt: Long = System.currentTimeMillis()
)
