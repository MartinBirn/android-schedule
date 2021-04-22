package com.grsu.schedule_project.home.schedule.di

import com.grsu.schedule_project.core.preferences.di.PRIVATE_STORAGE
import com.grsu.schedule_project.model.repositories.ScheduleRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dayRepositoryModule = module {
    single {
        ScheduleRepository(
            preferences = get(named(PRIVATE_STORAGE)),
            scheduleService = get(),
            dayDao = get(),
            lessonDao = get()
        )
    }
}