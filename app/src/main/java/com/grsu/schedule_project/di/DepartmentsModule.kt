package com.grsu.schedule_project.di

import com.grsu.schedule_project.presentation.screen.home.departments.DepartmentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val departmentModule = module {
    viewModel {
        DepartmentsViewModel(router = get(named(SCHEDULE_CONTAINER)), departmentRepository = get())
    }
}