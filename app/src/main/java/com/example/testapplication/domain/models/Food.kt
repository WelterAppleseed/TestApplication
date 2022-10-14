package com.example.testapplication.domain.models

import com.google.gson.annotations.SerializedName

data class Food(
    @SerializedName("id")
    var id: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("image")
    var image: String,
    @SerializedName("restaurantChain")
    var restaurantChain: String,
    @SerializedName("servingSize")
    var servingSize: String,
    @SerializedName("cost")
    var cost: Int = 385
)
