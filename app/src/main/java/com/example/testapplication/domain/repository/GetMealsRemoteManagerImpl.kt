package com.example.testapplication.domain.repository

import com.example.testapplication.domain.models.Meals
import com.example.testapplication.domain.models.remote.Api
import javax.inject.Inject

class GetMealsRemoteManagerImpl @Inject constructor(private val api: Api): GetMealsRemoteManager {

    override suspend fun getFood(query: String, number: Int): Meals {
        return api.getFood(query, number)
    }

}