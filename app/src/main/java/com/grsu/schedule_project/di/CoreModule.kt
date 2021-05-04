package com.grsu.schedule_project.di

import com.grsu.schedule_project.common.utils.DateUtils
import com.grsu.schedule_project.common.utils.Utils
import org.koin.dsl.module

val commonModule = module {
    single { Utils(context = get()) }

    single { DateUtils(context = get()) }
}