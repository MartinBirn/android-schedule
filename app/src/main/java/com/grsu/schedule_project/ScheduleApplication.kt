package com.grsu.schedule_project

import android.app.Application
import com.grsu.schedule_project.core.preferences.di.preferencesModule
import com.grsu.schedule_project.database.di.databaseModule
import com.grsu.schedule_project.network.di.networkModule
import com.grsu.schedule_project.schedule.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ScheduleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ScheduleApplication)
            modules(
                preferencesModule,
                networkModule,
                databaseModule,
                teacherRepositoryModule,
                facultyRepositoryModule,
                departmentRepositoryModule,
                groupRepositoryModule,
                dayRepositoryModule
            )
        }
    }
}