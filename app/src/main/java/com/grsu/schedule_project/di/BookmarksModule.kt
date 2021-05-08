package com.grsu.schedule_project.di

import com.grsu.schedule_project.presentation.screen.home.bookmarks.BookmarksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookmarksModule = module {
    viewModel { BookmarksViewModel(router = get(), bookmarkRepository = get()) }
}