package com.grsu.schedule_project.di

import com.grsu.schedule_project.data.source.group.GroupRepository
import com.grsu.schedule_project.data.source.group.local.GroupLocalDataSource
import com.grsu.schedule_project.data.source.group.remote.GroupRemoteDataSource
import org.koin.core.qualifier.named
import org.koin.dsl.module

val groupRepositoryModule = module {
    single { GroupRemoteDataSource(groupService = get(), utils = get()) }

    single { GroupLocalDataSource(groupDao = get()) }

    single {
        GroupRepository(
            remoteDataSource = get(),
            localDataSource = get(),
            preferences = get(named(PRIVATE_STORAGE))
        )
    }
}