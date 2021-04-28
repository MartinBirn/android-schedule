package com.grsu.schedule_project.data.source.schedule

import android.content.SharedPreferences
import com.grsu.schedule_project.common.APP_LANGUAGE_KEY
import com.grsu.schedule_project.common.preferences.util.string
import com.grsu.schedule_project.data.model.dto.DayDto
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Failure.DatabaseFailure
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.data.model.mappers.toDayDbo
import com.grsu.schedule_project.data.source.schedule.local.ScheduleLocalDataSource
import com.grsu.schedule_project.data.source.schedule.remote.ScheduleRemoteDataSource

class ScheduleRepository(
    private val remoteDataSource: ScheduleRemoteDataSource,
    private val localDataSource: ScheduleLocalDataSource,
    private val preferences: SharedPreferences
) {

    private val appLanguage by preferences.string(APP_LANGUAGE_KEY)

    suspend fun getGroupSchedule(
        groupId: String,
        dateStart: String? = null,
        dateEnd: String? = null
    ): RepoResult<*, *> {
        val dayDboList = localDataSource.getGroupSchedule(groupId = groupId)
        if (dayDboList is Success && dayDboList.response.isEmpty()) {
            val tempDayDtoList: List<DayDto>
            when (val apiResult = remoteDataSource.getGroupSchedule(
                groupId = groupId,
                dateStart = dateStart,
                dateEnd = dateEnd,
                language = appLanguage
            )) {
                is Success -> tempDayDtoList = apiResult.response
                else -> return apiResult
            }
            try {
                val tempDayDboList = tempDayDtoList.map { it.toDayDbo() }
                val tempLessonsDboList = tempDayDboList.mapNotNull { it.lessons }.flatten()
                localDataSource.insertAndDeletePrevious(*tempDayDboList.toTypedArray())
                localDataSource.insertAndDeletePrevious(*tempLessonsDboList.toTypedArray())

                return tempDayDboList.let(::Success)
            } catch (e: Throwable) {
                return e.let(::DatabaseFailure)
            }
        }
        return dayDboList
    }

    suspend fun deleteSchedule() {
        localDataSource.deleteAll()
    }
}