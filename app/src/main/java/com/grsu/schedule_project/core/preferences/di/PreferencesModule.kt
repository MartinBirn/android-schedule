package com.grsu.schedule_project.core.preferences.di

import android.content.Context
import androidx.preference.PreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

const val DEFAULT_STORAGE = "default_storage"
const val PRIVATE_STORAGE = "private_storage"
const val PRIVATE_STORAGE_NAME = "private_storage_name"

val preferencesModule = module {
    single(named(DEFAULT_STORAGE)) {
        PreferenceManager.getDefaultSharedPreferences(
            androidContext()
        )
    }
    single(named(PRIVATE_STORAGE)) {
        androidContext().getSharedPreferences(
            PRIVATE_STORAGE_NAME,
            Context.MODE_PRIVATE
        )
    }
}