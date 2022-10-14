package com.example.testapplication.domain.usecase

import com.example.testapplication.domain.models.Meals
import com.example.testapplication.domain.repository.GetMealsLocalManager
import com.example.testapplication.domain.usecase.base.SuspendReturnUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFoodLocalUseCase @Inject constructor(private val getMealsLocalManager: GetMealsLocalManager) :
    SuspendReturnUseCase<Flow<Meals>> {

    override suspend fun execute(): Flow<Meals> {
        return getMealsLocalManager.getFood()
    }
}