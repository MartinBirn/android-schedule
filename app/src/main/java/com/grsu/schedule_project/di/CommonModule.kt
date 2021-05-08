package com.grsu.schedule_project.di

import com.grsu.schedule_project.common.utils.ContextManager
import com.grsu.schedule_project.common.utils.DateUtils
import com.grsu.schedule_project.common.utils.Utils
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val commonModule = module {
    single { ContextManager(androidContext = androidContext()) }

    single { Utils(contextManager = get()) }

    single { DateUtils(context = get()) }
}