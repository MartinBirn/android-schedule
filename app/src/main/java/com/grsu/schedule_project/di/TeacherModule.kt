package com.grsu.schedule_project.di

import com.grsu.schedule_project.presentation.screen.home.teacher.TeacherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val teacherModule = module {
    viewModel { (teacherId: String?) ->
        TeacherViewModel(
            router = get(named(SCHEDULE_CONTAINER)),
            utils = get(),
            teacherRepository = get(),
            teacherId = teacherId
        )
    }
}