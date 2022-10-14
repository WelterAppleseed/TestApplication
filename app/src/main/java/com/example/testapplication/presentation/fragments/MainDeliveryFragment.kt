package com.example.testapplication.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapplication.databinding.MainDeliveryFragmentBinding
import com.example.testapplication.domain.models.Food
import com.example.testapplication.domain.models.remote.Status
import com.example.testapplication.presentation.base.BaseFragment
import com.example.testapplication.presentation.vm.FoodViewModel
import com.example.testapplication.util.objs.FoodConstants
import com.example.testapplication.util.objs.TabItems

class MainDeliveryFragment :
    BaseFragment<MainDeliveryFragmentBinding>(MainDeliveryFragmentBinding::inflate) {
    private val foodViewModel: FoodViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPizza()
        initTabs()
    }

    private fun getPizza() {
        binding.apply {
            foodViewModel.getFoodRemote(FoodConstants.PIZZA, FoodConstants.NUMBER)
            foodViewModel.getFoodLiveData().observe(viewLifecycleOwner) {
                if (it.status == Status.SUCCESS) {
                    foodViewModel.addMeals(it.data!!)
                    initRecycler(it.data.menuItems)
                }
            }
        }
    }

    private fun initRecycler(pizzas: List<Food>) {
        binding.apply {
            nestedRecycler.adapter = MainDeliveryFoodAdapter(pizzas)
            nestedRecycler.layoutManager = LinearLayoutManager(context)

            bannersRecycler.adapter = MainDeliveryBannersAdapter()
            bannersRecycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun initTabs() {
        binding.layoutTabToolbar.apply {
            slidingTabs.addTab(slidingTabs.newTab().setText(TabItems.PIZZA))
            slidingTabs.addTab(slidingTabs.newTab().setText(TabItems.COMBO))
            slidingTabs.addTab(slidingTabs.newTab().setText(TabItems.DESSERTS))
            slidingTabs.addTab(slidingTabs.newTab().setText(TabItems.DRINKS))
        }
    }
}