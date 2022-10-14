package com.example.testapplication.domain.repository

import com.example.testapplication.domain.models.Meals

interface AddMealsRepository {
    suspend fun addMeals(meals: Meals)
}