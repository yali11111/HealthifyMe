package com.example.BottomNavMenu.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.BottomNavMenu.core.database.converter.DateConverter
import com.example.BottomNavMenu.core.database.converter.HealthDataConverter
import com.example.BottomNavMenu.data.local.dao.CaloriesDao
import com.example.BottomNavMenu.data.local.dao.SleepDao
import com.example.BottomNavMenu.data.local.dao.StepsDao
import com.example.BottomNavMenu.data.local.dao.UserDao
import com.example.BottomNavMenu.data.local.dao.WeightDao
import com.example.BottomNavMenu.data.local.entity.CaloriesEntity
import com.example.BottomNavMenu.data.local.entity.SleepEntity
import com.example.BottomNavMenu.data.local.entity.StepsEntity
import com.example.BottomNavMenu.data.local.entity.UserEntity
import com.example.BottomNavMenu.data.local.entity.WeightEntity

@Database(
    entities = [
        UserEntity::class,
        StepsEntity::class,
        WeightEntity::class,
        CaloriesEntity::class,
        SleepEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(
    DateConverter::class,
    HealthDataConverter::class
)
abstract class HealthDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun stepsDao(): StepsDao

    abstract fun weightDao(): WeightDao

    abstract fun caloriesDao(): CaloriesDao

    abstract fun sleepDao(): SleepDao
}
