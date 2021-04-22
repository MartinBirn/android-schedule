package com.grsu.schedule_project.database.di

import androidx.room.Room
import com.grsu.schedule_project.database.ScheduleDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidApplication(), ScheduleDatabase::class.java, "schedule-db")
            .build()
    }

    single {
        get<ScheduleDatabase>().teacherDao()
    }

    single {
        get<ScheduleDatabase>().facultyDao()
    }

    single {
        get<ScheduleDatabase>().departmentDao()
    }

    single {
        get<ScheduleDatabase>().groupDao()
    }

    single {
        get<ScheduleDatabase>().dayDao()
    }

    single {
        get<ScheduleDatabase>().lessonDao()
    }

    single {
        get<ScheduleDatabase>().lessonTeacherDao()
    }
}