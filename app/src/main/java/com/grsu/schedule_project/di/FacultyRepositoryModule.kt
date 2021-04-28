package com.grsu.schedule_project.di

import com.grsu.schedule_project.data.source.faculty.FacultyRepository
import com.grsu.schedule_project.data.source.faculty.local.FacultyLocalDataSource
import com.grsu.schedule_project.data.source.faculty.remote.FacultyRemoteDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

val facultyRepositoryModule = module {
    single { FacultyRemoteDataSource(facultyService = get(), utils = get()) }

    single { FacultyLocalDataSource(facultyDao = get()) }

    single {
        FacultyRepository(
            remoteDataSource = get(),
            localDataSource = get(),
            preferences = get(named(PRIVATE_STORAGE))
        )
    }
}