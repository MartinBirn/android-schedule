package com.grsu.schedule_project.di

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.dsl.module

val navigationModule = module {
    single { Cicerone.create(Router()) }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
    single { get<Cicerone<Router>>().router }
}