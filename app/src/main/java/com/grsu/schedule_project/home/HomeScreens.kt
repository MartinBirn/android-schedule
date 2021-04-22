package com.grsu.schedule_project.home

import com.github.terrakok.cicerone.androidx.ActivityScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.grsu.schedule_project.home.schedule.ScheduleFragment
import com.grsu.schedule_project.home.settings.SettingsFragment

object HomeScreens {

    fun homeScreen() = ActivityScreen { HomeActivity.getIntent(it) }

    fun scheduleScreen() = FragmentScreen { ScheduleFragment.getNewInstance() }

    fun settingsScreen() = FragmentScreen { SettingsFragment.getNewInstance() }
}