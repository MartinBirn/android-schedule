package com.grsu.schedule_project.home

import com.github.terrakok.cicerone.androidx.ActivityScreen

object HomeScreens {

    fun homeScreen() = ActivityScreen { HomeActivity.getIntent(it) }
}