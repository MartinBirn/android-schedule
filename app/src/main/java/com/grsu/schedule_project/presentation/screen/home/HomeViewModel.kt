package com.grsu.schedule_project.presentation.screen.home

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.grsu.schedule_project.common.navigation.ScheduleRouter

class HomeViewModel(
    private val router: ScheduleRouter
) : ViewModel() {

    fun onBackPressed() {
        router.backInActivity()
    }

    fun onGroupClick(containerTag: String, groupId: String?, groupTitle: String?) {
        router.openScheduleInActivity(containerTag, groupId, groupTitle)
    }

    fun switchTab(screen: FragmentScreen, title: String) {
        router.switchTab(screen, title)
    }
}