package com.grsu.schedule_project.splashscreen.di

import com.grsu.schedule_project.splashscreen.SplashScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashScreenModule = module {
    viewModel { SplashScreenViewModel(router = get()) }
}