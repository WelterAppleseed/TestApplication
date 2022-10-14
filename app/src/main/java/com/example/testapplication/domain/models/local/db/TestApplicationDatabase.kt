package com.example.testapplication.domain.models.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.testapplication.domain.models.local.MealsConverter
import com.example.testapplication.domain.models.local.dao.MealsDao
import com.example.testapplication.domain.models.Meals

@TypeConverters(value = [MealsConverter::class])
@Database(entities = [Meals::class], version = 1)
abstract class TestApplicationDatabase: RoomDatabase() {
    abstract fun foodDao(): MealsDao
}