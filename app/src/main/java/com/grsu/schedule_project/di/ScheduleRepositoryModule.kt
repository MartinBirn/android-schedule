package com.grsu.schedule_project.di

import com.grsu.schedule_project.data.source.schedule.ScheduleRepository
import com.grsu.schedule_project.data.source.schedule.local.ScheduleLocalDataSource
import com.grsu.schedule_project.data.source.schedule.remote.ScheduleRemoteDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

val scheduleRepositoryModule = module {
    single { ScheduleRemoteDataSource(groupScheduleService = get(), utils = get()) }

    single { ScheduleLocalDataSource(dayDao = get(), lessonDao = get()) }

    single {
        ScheduleRepository(
            remoteDataSource = get(),
            localDataSource = get(),
            preferences = get(named(PRIVATE_STORAGE))
        )
    }
}