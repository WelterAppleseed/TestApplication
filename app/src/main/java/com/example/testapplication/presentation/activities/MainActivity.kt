package com.example.testapplication.presentation.activities

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.testapplication.R
import com.example.testapplication.presentation.vm.NavigationViewModel
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val navigationViewModel: NavigationViewModel by viewModels()

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private var navigator: AppNavigator = AppNavigator(this, R.id.main_fragment_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        updateNavigator()
        navigationViewModel.goToUserCardsFragment()
    }

    private fun updateNavigator() {
        navigatorHolder.removeNavigator()
        navigator = object : AppNavigator(this, R.id.main_fragment_container) {
            override fun commitNewFragmentScreen(screen: FragmentScreen, addToBackStack: Boolean) {
                val fragment = screen.createFragment(fragmentFactory)
                val transaction = fragmentManager.beginTransaction()
                transaction.setReorderingAllowed(true)
                setupFragmentTransaction(
                    screen,
                    transaction,
                    fragmentManager.findFragmentById(containerId),
                    fragment
                )
                if (screen.clearContainer) {
                    transaction.replace(containerId, fragment, screen.screenKey)
                } else {
                    transaction.add(containerId, fragment, screen.screenKey)
                }
                if (addToBackStack) {
                    transaction.addToBackStack(screen.screenKey)
                    localStackCopy.add(screen.screenKey)
                }
                transaction.commit()
            }
        }
        navigatorHolder.setNavigator(navigator)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigator.let { navigatorHolder.setNavigator(it) }
    }
}