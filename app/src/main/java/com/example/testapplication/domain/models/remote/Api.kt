package com.example.testapplication.domain.models.remote

import com.example.testapplication.domain.models.Meals
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("/food/menuItems/search")
    suspend fun getFood(
        @Query("query") query: String,
        @Query("number") number: Int,
    ): Meals
}