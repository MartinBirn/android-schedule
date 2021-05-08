package com.grsu.schedule_project.presentation.screen.home.settingscontainer

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.navigation.ScheduleRouter

class SettingsContainerViewModel(
    private val router: ScheduleRouter
) : ViewModel() {

    fun replaceScreen(screen: FragmentScreen) {
        router.replaceScreen(screen)
    }

    fun navigateTo(screen: FragmentScreen) {
        router.navigateTo(screen)
    }

    fun onBackPressed() {
        router.backInContainer(R.id.scheduleContainer)
    }
}