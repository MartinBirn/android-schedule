package com.grsu.schedule_project.home.di

import com.grsu.schedule_project.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel { HomeViewModel(router = get()) }
}