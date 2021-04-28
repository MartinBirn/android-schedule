package com.grsu.schedule_project

import android.app.Application
import com.grsu.schedule_project.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ScheduleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ScheduleApplication)
            modules(
                splashScreenModule,
                preferencesModule,
                commonModule,
                navigationModule,
                scheduleContainerNavigationModule,
                homeModule,
                scheduleContainerModule,
                departmentModule,
                networkModule,
                databaseModule,
                teacherRepositoryModule,
                facultyRepositoryModule,
                departmentRepositoryModule,
                groupRepositoryModule,
                scheduleRepositoryModule
            )
        }
    }
}