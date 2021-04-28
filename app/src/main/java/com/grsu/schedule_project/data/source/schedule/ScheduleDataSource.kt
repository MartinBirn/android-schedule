package com.grsu.schedule_project.data.source.schedule

import com.grsu.schedule_project.data.model.errorhandling.RepoResult

interface ScheduleDataSource {

    suspend fun getGroupSchedule(
        groupId: String,
        dateStart: String? = null,
        dateEnd: String? = null,
        language: String? = null
    ): RepoResult<*, *>
}