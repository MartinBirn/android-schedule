package com.grsu.schedule_project

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import com.grsu.schedule_project.common.locale.RuntimeLocaleChanger
import com.grsu.schedule_project.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.*

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
                settingsContainerNavigationModule,
                homeModule,
                scheduleContainerModule,
                departmentModule,
                facultyModule,
                courseModule,
                groupModule,
                scheduleModule,
                teacherModule,
                bookmarksModule,
                settingsContainerModule,
                settingsModule,
                languagesModule,
                themesModule,
                networkModule,
                databaseModule,
                teacherRepositoryModule,
                facultyRepositoryModule,
                departmentRepositoryModule,
                groupRepositoryModule,
                scheduleRepositoryModule,
                bookmarkRepositoryModule
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base?.let { RuntimeLocaleChanger(it).wrapContext() });
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        RuntimeLocaleChanger(this).overrideLocale()
    }
}