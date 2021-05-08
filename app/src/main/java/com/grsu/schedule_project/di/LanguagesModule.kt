package com.grsu.schedule_project.di

import com.grsu.schedule_project.presentation.screen.home.languages.LanguagesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val languagesModule = module {
    viewModel {
        LanguagesViewModel(
            router = get(named(SETTINGS_CONTAINER)),
            preferences = get(named(PRIVATE_STORAGE)),
            departmentRepository = get(),
            facultyRepository = get()
        )
    }
}