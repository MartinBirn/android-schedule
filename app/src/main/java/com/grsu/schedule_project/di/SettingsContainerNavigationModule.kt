package com.grsu.schedule_project.di

import com.github.terrakok.cicerone.Cicerone
import com.grsu.schedule_project.common.navigation.ScheduleRouter
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val SETTINGS_CONTAINER = "settings_container"

val settingsContainerNavigationModule = module {
    single(named(SETTINGS_CONTAINER)) {
        Cicerone.create(ScheduleRouter())
    }
    single(named(SETTINGS_CONTAINER)) {
        get<Cicerone<ScheduleRouter>>(qualifier = named(SETTINGS_CONTAINER)).getNavigatorHolder()
    }
    single(named(SETTINGS_CONTAINER)) {
        get<Cicerone<ScheduleRouter>>(qualifier = named(SETTINGS_CONTAINER)).router
    }
}