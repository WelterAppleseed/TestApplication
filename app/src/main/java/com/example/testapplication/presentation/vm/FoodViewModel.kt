package com.example.testapplication.presentation.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.testapplication.domain.models.Meals
import com.example.testapplication.domain.models.remote.Event
import com.example.testapplication.domain.usecase.AddMealsUseCase
import com.example.testapplication.domain.usecase.GetFoodLocalUseCase
import com.example.testapplication.domain.usecase.GetFoodRemoteUseCase
import com.example.testapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodViewModel @Inject constructor(
    private val getFoodRemoteUseCase: GetFoodRemoteUseCase,
    private val getFoodLocalUseCase: GetFoodLocalUseCase,
    private val addMealsUseCase: AddMealsUseCase
) : BaseViewModel() {
    private val simpleLiveData = MutableLiveData<Event<Meals>>()

    init {
        getFoodLocal()
    }
    fun getFoodRemote(query: String, number: Int) {
        simpleLiveData.postValue(Event.loading())
        getFoodRemoteUseCase.saveOptions(query, number)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getFoodRemoteUseCase.execute()
                if (response.menuItems.isNotEmpty()) {
                    simpleLiveData.postValue(Event.success(response))
                } else {
                    simpleLiveData.postValue(Event.error(null))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                simpleLiveData.postValue(Event.error(null))
            }
        }
    }
    private fun getFoodLocal() {
        viewModelScope.launch {
            getFoodLocalUseCase.execute()
                .distinctUntilChanged()
                .collect {
                    if (it.menuItems.isNotEmpty()) {
                        simpleLiveData.postValue(Event.success(it))
                    } else {
                        simpleLiveData.postValue(Event.error(null))
                    }
                }
        }
    }
    fun getFoodLiveData() = simpleLiveData

    fun addMeals(meals: Meals) {
        viewModelScope.launch {
            addMealsUseCase.saveInput(meals)
            addMealsUseCase.execute()
        }
    }
}