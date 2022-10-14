package com.example.testapplication.presentation.vm

import com.example.testapplication.presentation.base.BaseViewModel
import com.example.testapplication.util.objs.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.github.terrakok.cicerone.Router

@HiltViewModel
class NavigationViewModel @Inject constructor(
    private val router: Router
): BaseViewModel() {
    fun goToUserCardsFragment() = router.navigateTo(Screen.mainDeliveryScreen())
}