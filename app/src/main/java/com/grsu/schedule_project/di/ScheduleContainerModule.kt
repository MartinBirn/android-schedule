package com.grsu.schedule_project.di

import com.grsu.schedule_project.presentation.screen.home.schedulecontainer.ScheduleContainerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val scheduleContainerModule = module {
    viewModel { ScheduleContainerViewModel(router = get(named(SCHEDULE_CONTAINER))) }
}