package com.example.testapplication.domain.models.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testapplication.domain.models.Meals
import kotlinx.coroutines.flow.Flow

@Dao
interface MealsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMeals(meals: Meals)
    @Query("SELECT * FROM meals")
    fun getMeals(): Flow<Meals>
}