package com.example.testapplication.domain.repository

import com.example.testapplication.domain.models.Food
import com.example.testapplication.domain.models.Meals
import kotlinx.coroutines.flow.Flow

interface GetMealsLocalManager {
    fun getFood(): Flow<Meals>
}