package com.example.BottomNavMenu.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "steps",
    indices = [
        Index(value = ["date"], unique = true)
    ]
)
data class StepsEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val date: LocalDate,

    val stepsCount: Int,

    val dailyGoal: Int = 10000,

    val recordedAt: Long = System.currentTimeMillis()
)
