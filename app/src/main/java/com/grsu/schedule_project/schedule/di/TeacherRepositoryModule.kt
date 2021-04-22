package com.grsu.schedule_project.schedule.di

import com.grsu.schedule_project.core.preferences.di.PRIVATE_STORAGE
import com.grsu.schedule_project.model.repositories.TeacherRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val teacherRepositoryModule = module {
    single {
        TeacherRepository(
            preferences = get(named(PRIVATE_STORAGE)),
            scheduleService = get(),
            teacherDao = get()
        )
    }
}