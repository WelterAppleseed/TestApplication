package com.example.testapplication.presentation.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapplication.R
import com.example.testapplication.databinding.FoodItemBinding
import com.example.testapplication.domain.models.Food

class MainDeliveryFoodAdapter(
    private val foodList: List<Food>
) : RecyclerView.Adapter<MainDeliveryFoodAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: FoodItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                val food = foodList[position]
                foodTitle.text = food.title
                foodDescription.text = foodDescription.context.getString(R.string.food_descr, food.restaurantChain, food.servingSize)
                Glide.with(binding.root)
                    .load(food.image)
                    .placeholder(R.drawable.placeholder)
                    .into(foodImage)
            }
        }
    }

    override fun getItemCount(): Int {
       return foodList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FoodItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }
}
