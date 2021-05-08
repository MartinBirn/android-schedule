package com.grsu.schedule_project.presentation.screen.home.settings

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Screen
import com.grsu.schedule_project.common.navigation.ScheduleRouter

class SettingsViewModel(
    private val router: ScheduleRouter
) : ViewModel() {

    fun navigateTo(screen: Screen) {
        router.navigateTo(screen)
    }

    fun onBackPressed() {
        router.exit()
    }
}