package com.grsu.schedule_project.di

import com.github.terrakok.cicerone.Cicerone
import com.grsu.schedule_project.common.navigation.ScheduleRouter
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val SCHEDULE_CONTAINER = "schedule_container"

val scheduleContainerNavigationModule = module {
    single(named(SCHEDULE_CONTAINER)) {
        Cicerone.create(ScheduleRouter())
    }
    single(named(SCHEDULE_CONTAINER)) {
        get<Cicerone<ScheduleRouter>>(qualifier = named(SCHEDULE_CONTAINER)).getNavigatorHolder()
    }
    single(named(SCHEDULE_CONTAINER)) {
        get<Cicerone<ScheduleRouter>>(qualifier = named(SCHEDULE_CONTAINER)).router
    }
}