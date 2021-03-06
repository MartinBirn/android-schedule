package com.grsu.schedule_project.common.navigation

import androidx.annotation.IdRes
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.grsu.schedule_project.common.navigation.commands.*

class ScheduleRouter : Router() {

    fun switchTab(screen: FragmentScreen, tag: String) {
        executeCommands(SwitchTabCommand(screen, tag))
    }

    fun backInActivity() {
        executeCommands(BackInActivity())
    }

    fun backInContainer(@IdRes id: Int) {
        executeCommands(BackInContainer(id))
    }

    fun openScheduleInActivity(containerTag: String, groupId: String?, groupTitle: String?) {
        executeCommands(OpenScheduleInActivity(containerTag, groupId, groupTitle))
    }

    fun openSchedule(groupId: String?, groupTitle: String?) {
        executeCommands(OpenSchedule(groupId, groupTitle))
    }

    fun restartActivity(vararg extraPair: Pair<String, String>) {
        executeCommands(RestartActivity(*extraPair))
    }
}