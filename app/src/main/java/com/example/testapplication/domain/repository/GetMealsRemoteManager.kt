package com.example.testapplication.domain.repository

import com.example.testapplication.domain.models.Meals

interface GetMealsRemoteManager {
    suspend fun getFood(query: String, number: Int): Meals
}