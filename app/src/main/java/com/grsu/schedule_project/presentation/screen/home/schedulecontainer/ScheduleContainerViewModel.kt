package com.grsu.schedule_project.presentation.screen.home.schedulecontainer

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen

class ScheduleContainerViewModel(
    private val router: Router
) : ViewModel() {

    fun replaceScreen(screen: FragmentScreen) {
        router.replaceScreen(screen)
    }
}