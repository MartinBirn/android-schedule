package com.grsu.schedule_project.home.schedule.di

import com.grsu.schedule_project.core.preferences.di.PRIVATE_STORAGE
import com.grsu.schedule_project.model.repositories.DepartmentRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val departmentRepositoryModule = module {
    single {
        DepartmentRepository(
            preferences = get(named(PRIVATE_STORAGE)),
            scheduleService = get(),
            departmentDao = get()
        )
    }
}