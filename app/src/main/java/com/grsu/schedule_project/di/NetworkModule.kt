package com.grsu.schedule_project.di

import com.grsu.schedule_project.data.network.BASE_URL
import com.grsu.schedule_project.data.network.errorhandling.ErrorConverterFactory
import com.grsu.schedule_project.data.network.retrofit.*
import com.slack.eithernet.ApiResultCallAdapterFactory
import com.slack.eithernet.ApiResultConverterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .addCallAdapterFactory(ApiResultCallAdapterFactory)
            .addConverterFactory(
                ApiResultConverterFactory
            )
            .addConverterFactory(
                ErrorConverterFactory()
            )
            .addConverterFactory(
                MoshiConverterFactory.create()
            )
            .baseUrl(BASE_URL)
            .client(get())
            .build()
    }

    single { get<Retrofit>().create(DepartmentService::class.java) }

    single { get<Retrofit>().create(FacultyService::class.java) }

    single { get<Retrofit>().create(GroupService::class.java) }

    single { get<Retrofit>().create(GroupScheduleService::class.java) }

    single { get<Retrofit>().create(TeacherService::class.java) }
}