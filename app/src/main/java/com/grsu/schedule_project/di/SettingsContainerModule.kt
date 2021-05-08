package com.grsu.schedule_project.di

import com.grsu.schedule_project.presentation.screen.home.settingscontainer.SettingsContainerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val settingsContainerModule = module {
    viewModel { SettingsContainerViewModel(router = get(named(SETTINGS_CONTAINER))) }
}