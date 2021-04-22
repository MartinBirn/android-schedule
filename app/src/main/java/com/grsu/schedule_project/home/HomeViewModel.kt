package com.grsu.schedule_project.home

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen

class HomeViewModel(
    private val router: Router
) : ViewModel() {

    init {
        router.replaceScreen(HomeScreens.scheduleScreen())
    }

    fun onBackPressed() {
        router.exit()
    }

    fun selectTab(screen: FragmentScreen) {
        router.replaceScreen(screen)
    }
}