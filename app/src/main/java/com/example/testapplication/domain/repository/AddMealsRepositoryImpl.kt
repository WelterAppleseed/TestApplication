package com.example.testapplication.domain.repository

import com.example.testapplication.domain.models.Meals
import com.example.testapplication.domain.models.local.dao.MealsDao
import javax.inject.Inject

class AddMealsRepositoryImpl @Inject constructor(private val mealsDao: MealsDao) :
    AddMealsRepository {
    override suspend fun addMeals(meals: Meals) {
        mealsDao.addMeals(meals)
    }

}