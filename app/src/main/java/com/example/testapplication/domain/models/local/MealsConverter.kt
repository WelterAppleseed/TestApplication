package com.example.testapplication.domain.models.local

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.testapplication.domain.models.Food
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@TypeConverters
class MealsConverter {
    @TypeConverter
    fun fromFoodList(food: List<Food>): String {
        return Gson().toJson(food)
    }

    @TypeConverter
    fun toFoodList(string: String): List<Food> {
        val myType = object : TypeToken<List<Food>>() {}.type
        return Gson().fromJson(string, myType)
    }
}