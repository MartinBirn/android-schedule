package com.grsu.schedule_project

import android.app.Application
import com.grsu.schedule_project.core.navigation.di.navigationModule
import com.grsu.schedule_project.core.preferences.di.preferencesModule
import com.grsu.schedule_project.database.di.databaseModule
import com.grsu.schedule_project.home.di.homeModule
import com.grsu.schedule_project.home.schedule.di.*
import com.grsu.schedule_project.network.di.networkModule
import com.grsu.schedule_project.splashscreen.di.splashScreenModule
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
                navigationModule,
                homeModule,
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