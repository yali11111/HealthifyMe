package com.example.BottomNavMenu.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "weights",
    indices = [
        Index(value = ["measuredAt"])
    ]
)
data class WeightEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val weightKg: Double,

    val measuredAt: Long = System.currentTimeMillis(),

    val note: String? = null
)
