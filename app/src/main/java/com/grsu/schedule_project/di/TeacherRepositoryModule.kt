package com.grsu.schedule_project.di

import com.grsu.schedule_project.data.source.teacher.TeacherRepository
import com.grsu.schedule_project.data.source.teacher.local.TeacherLocalDataSource
import com.grsu.schedule_project.data.source.teacher.remote.TeacherRemoteDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

val teacherRepositoryModule = module {
    single { TeacherRemoteDataSource(teacherService = get(), utils = get()) }

    single { TeacherLocalDataSource(teacherDao = get()) }

    single {
        TeacherRepository(
            remoteDataSource = get(),
            localDataSource = get(),
            preferences = get(named(PRIVATE_STORAGE))
        )
    }
}