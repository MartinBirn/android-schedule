package com.grsu.schedule_project.di

import com.grsu.schedule_project.presentation.screen.home.schedule.ScheduleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val scheduleModule = module {
    viewModel { (groupId: String?, groupTitle: String?) ->
        ScheduleViewModel(
            router = get(named(SCHEDULE_CONTAINER)),
            utils = get(),
            scheduleRepository = get(),
            bookmarkRepository = get(),
            groupId = groupId,
            groupTitle = groupTitle
        )
    }
}