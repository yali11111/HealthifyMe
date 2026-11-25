package com.example.BottomNavMenu.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "calories",
    indices = [
        Index(value = ["date"], unique = true)
    ]
)
data class CaloriesEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val date: LocalDate,

    val basalMetabolicRate: Double,

    val dailyTarget: Double,

    val consumedCalories: Double = 0.0,

    val burnedCalories: Double = 0.0,

    val recordedAt: Long = System.currentTimeMillis()
)
