package com.example.testapplication.domain.usecase

import com.example.testapplication.domain.models.Meals
import com.example.testapplication.domain.repository.AddMealsRepository
import com.example.testapplication.domain.usecase.base.BaseVoidUseCase
import javax.inject.Inject

class AddMealsUseCase @Inject constructor(private val addMealsRepository: AddMealsRepository):
    BaseVoidUseCase {
    private var meals: Meals? = null
    fun saveInput(meals: Meals) = run { this.meals = meals }

    override suspend fun execute() {
        if (meals != null) addMealsRepository.addMeals(meals!!)
    }
}