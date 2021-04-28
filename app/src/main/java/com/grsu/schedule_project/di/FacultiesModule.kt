package com.grsu.schedule_project.di

import com.grsu.schedule_project.presentation.screen.home.faculties.FacultiesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val facultyModule = module {
    viewModel { (departmentId: String?) ->
        FacultiesViewModel(
            router = get(named(SCHEDULE_CONTAINER)),
            facultyRepository = get(),
            departmentId = departmentId
        )
    }
}