package com.grsu.schedule_project.di

import com.github.terrakok.cicerone.Cicerone
import com.grsu.schedule_project.common.navigation.ScheduleRouter
import org.koin.dsl.module

val navigationModule = module {
    single { Cicerone.create(ScheduleRouter()) }
    single { get<Cicerone<ScheduleRouter>>().getNavigatorHolder() }
    single { get<Cicerone<ScheduleRouter>>().router }
}