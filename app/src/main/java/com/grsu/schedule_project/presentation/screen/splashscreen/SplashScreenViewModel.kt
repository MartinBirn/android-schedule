package com.grsu.schedule_project.presentation.screen.splashscreen

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.grsu.schedule_project.presentation.screen.home.HomeScreens

class SplashScreenViewModel(
    private val router: Router
) : ViewModel() {

    init {
        router.replaceScreen(HomeScreens.homeScreen())
    }
}