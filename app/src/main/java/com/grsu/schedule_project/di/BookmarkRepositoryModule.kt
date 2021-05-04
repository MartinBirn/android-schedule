package com.grsu.schedule_project.di

import com.grsu.schedule_project.data.source.bookmark.BookmarkRepository
import com.grsu.schedule_project.data.source.bookmark.local.BookmarkLocalDataSource
import org.koin.dsl.module

val bookmarkRepositoryModule = module {
    single { BookmarkLocalDataSource(bookmarkDao = get()) }

    single { BookmarkRepository(localDataSource = get()) }
}