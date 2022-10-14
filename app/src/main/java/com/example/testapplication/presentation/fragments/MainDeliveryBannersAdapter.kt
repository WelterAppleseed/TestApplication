package com.example.testapplication.presentation.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapplication.R
import com.example.testapplication.databinding.BannerItemBinding

class MainDeliveryBannersAdapter : RecyclerView.Adapter<MainDeliveryBannersAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: BannerItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.apply {
                when (position) {
                    0 -> bannerImage.setImageResource(R.drawable.banner_one)
                    1 -> bannerImage.setImageResource(R.drawable.banner_two)
                    2 -> bannerImage.setImageResource(R.drawable.banner_three)
                }
            }
        }
    }

    override fun getItemCount(): Int {
       return 3
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            BannerItemBinding.inflate(
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
