package com.example.testapplication.util.objs

import com.example.testapplication.presentation.fragments.MainDeliveryFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screen {
    fun mainDeliveryScreen() = FragmentScreen { MainDeliveryFragment() }
}