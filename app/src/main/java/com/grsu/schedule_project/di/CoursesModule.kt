package com.grsu.schedule_project.di

import com.grsu.schedule_project.presentation.screen.home.courses.CoursesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val courseModule = module {
    viewModel { (departmentId: String?, facultyId: String?) ->
        CoursesViewModel(
            router = get(named(SCHEDULE_CONTAINER)),
            utils = get(),
            departmentId,
            facultyId
        )
    }
}