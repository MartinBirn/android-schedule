package com.grsu.schedule_project.di

import com.grsu.schedule_project.data.source.department.DepartmentRepository
import com.grsu.schedule_project.data.source.department.local.DepartmentLocalDataSource
import com.grsu.schedule_project.data.source.department.remote.DepartmentRemoteDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

val departmentRepositoryModule = module {
    single { DepartmentRemoteDataSource(departmentService = get(), utils = get()) }

    single { DepartmentLocalDataSource(departmentDao = get()) }

    single {
        DepartmentRepository(
            remoteDataSource = get(),
            localDataSource = get(),
            preferences = get(named(PRIVATE_STORAGE))
        )
    }
}