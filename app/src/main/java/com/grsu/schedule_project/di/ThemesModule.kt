package com.grsu.schedule_project.di

import com.grsu.schedule_project.presentation.screen.home.themes.ThemesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val themesModule = module {
    viewModel {
        ThemesViewModel(
            router = get(named(SETTINGS_CONTAINER)),
            preferences = get(named(PRIVATE_STORAGE))
        )
    }
}