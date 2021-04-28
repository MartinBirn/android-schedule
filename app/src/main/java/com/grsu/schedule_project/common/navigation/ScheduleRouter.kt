package com.grsu.schedule_project.common.navigation

import androidx.annotation.IdRes
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.grsu.schedule_project.common.navigation.commands.BackInActivity
import com.grsu.schedule_project.common.navigation.commands.BackInContainer
import com.grsu.schedule_project.common.navigation.commands.SwitchTabCommand

class ScheduleRouter : Router() {

    fun switchTab(screen: FragmentScreen, title: String) {
        executeCommands(SwitchTabCommand(screen, title))
    }

    fun backInActivity() {
        executeCommands(BackInActivity())
    }

    fun backInContainer(@IdRes id: Int) {
        executeCommands(BackInContainer(id))
    }
}