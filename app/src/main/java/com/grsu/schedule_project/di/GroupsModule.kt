package com.grsu.schedule_project.di

import com.grsu.schedule_project.presentation.screen.home.groups.GroupsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val groupModule = module {
    viewModel { (departmentId: String?, facultyId: String?, courseId: String?) ->
        GroupsViewModel(
            router = get(named(SCHEDULE_CONTAINER)),
            utils = get(),
            groupRepository = get(),
            departmentId,
            facultyId,
            courseId
        )
    }
}