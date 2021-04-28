package com.grsu.schedule_project.presentation.screen.splashscreen

import androidx.lifecycle.ViewModel
import com.grsu.schedule_project.common.navigation.ScheduleRouter
import com.grsu.schedule_project.presentation.screen.home.HomeScreens

class SplashScreenViewModel(
    private val router: ScheduleRouter
) : ViewModel() {

    init {
        router.replaceScreen(HomeScreens.homeScreen())
    }
}