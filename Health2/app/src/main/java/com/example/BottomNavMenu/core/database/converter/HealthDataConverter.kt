package com.example.BottomNavMenu.core.database.converter

import androidx.room.TypeConverter

enum class SleepQuality {
    VERY_POOR,
    POOR,
    AVERAGE,
    GOOD,
    EXCELLENT
}

class HealthDataConverter {

    @TypeConverter
    fun fromSleepQuality(value: SleepQuality?): String? {
        return value?.name
    }

    @TypeConverter
    fun toSleepQuality(value: String?): SleepQuality? {
        return value?.let {
            runCatching { SleepQuality.valueOf(it) }.getOrNull()
        }
    }
}
