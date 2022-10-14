package com.example.testapplication.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "meals")
data class Meals(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @SerializedName("type")
    var type: String?,
    @SerializedName("menuItems")
    var menuItems: List<Food>
)