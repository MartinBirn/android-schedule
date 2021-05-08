package com.grsu.schedule_project.di

import com.grsu.schedule_project.presentation.screen.home.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val settingsModule = module {
    viewModel {
        SettingsViewModel(router = get(named(SETTINGS_CONTAINER)))
    }
}