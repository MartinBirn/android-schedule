package com.grsu.schedule_project.di

import com.grsu.schedule_project.presentation.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(router = get()) }
}