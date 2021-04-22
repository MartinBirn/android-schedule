package com.grsu.schedule_project.splashscreen

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.grsu.schedule_project.home.HomeScreens

class SplashScreenViewModel(
    private val router: Router
) : ViewModel() {

    init {
        router.replaceScreen(HomeScreens.homeScreen())
    }
}