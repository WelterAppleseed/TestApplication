package com.example.testapplication.domain.usecase

import com.example.testapplication.domain.models.Meals
import com.example.testapplication.domain.repository.GetMealsRemoteManager
import com.example.testapplication.domain.usecase.base.SuspendReturnUseCase
import javax.inject.Inject

class GetFoodRemoteUseCase @Inject constructor(private val getMealsRemoteManager: GetMealsRemoteManager) :
    SuspendReturnUseCase<Meals> {
    private var query = ""
    private var number = 0
    fun saveOptions(query: String, number: Int) = kotlin.run {
        this.query = query
        this.number = number
    }

    override suspend fun execute(): Meals {
        val wrapper = getMealsRemoteManager.getFood(query, number)
        println(wrapper)
        return wrapper
    }
}