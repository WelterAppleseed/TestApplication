package com.example.testapplication.domain.repository

import com.example.testapplication.domain.models.Meals
import com.example.testapplication.domain.models.local.dao.MealsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealsLocalManagerImpl  @Inject constructor(private val mealsDao: MealsDao): GetMealsLocalManager {

    override fun getFood(): Flow<Meals> {
        return mealsDao.getMeals()
    }

}