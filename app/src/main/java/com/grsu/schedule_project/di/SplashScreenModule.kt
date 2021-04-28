package com.grsu.schedule_project.di

import com.grsu.schedule_project.presentation.screen.splashscreen.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashScreenModule = module {
    viewModel { SplashScreenViewModel(router = get()) }
}