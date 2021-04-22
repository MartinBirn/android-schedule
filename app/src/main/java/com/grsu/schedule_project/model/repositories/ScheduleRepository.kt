package com.grsu.schedule_project.model.repositories

import android.content.SharedPreferences
import com.grsu.schedule_project.core.APP_LANGUAGE_KEY
import com.grsu.schedule_project.core.preferences.util.string
import com.grsu.schedule_project.database.dao.DayDao
import com.grsu.schedule_project.database.dao.LessonDao
import com.grsu.schedule_project.model.dto.DayDto
import com.grsu.schedule_project.model.errorhandling.RepoResult
import com.grsu.schedule_project.model.errorhandling.RepoResult.Failure.*
import com.grsu.schedule_project.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.model.mappers.toDayDbo
import com.grsu.schedule_project.network.retrofit.ApiService
import com.slack.eithernet.ApiResult

class ScheduleRepository(
    private val preferences: SharedPreferences,
    private val scheduleService: ApiService,
    private val dayDao: DayDao,
    private val lessonDao: LessonDao
) {

    private val appLanguage by preferences.string(APP_LANGUAGE_KEY)

    suspend fun getGroupSchedule(
        groupId: String,
        dateStart: String? = null,
        dateEnd: String? = null
    ): RepoResult<*, *> {
        val daysWithLessonsDbo = dayDao.getDaysWithLessons()
        if (daysWithLessonsDbo.isNullOrEmpty()) {
            val tempDtoData: List<DayDto>?
            when (val apiResult = scheduleService.getGroupSchedule(
                groupId = groupId,
                dateStart = dateStart,
                dateEnd = dateEnd,
                language = appLanguage
            )) {
                is ApiResult.Success -> {
                    tempDtoData = apiResult.response.days
                }
                is ApiResult.Failure.NetworkFailure -> {
                    return apiResult.error.let(::NetworkFailure)
                }
                is ApiResult.Failure.UnknownFailure -> {
                    return apiResult.error.let(::UnknownFailure)
                }
                is ApiResult.Failure.HttpFailure -> {
                    return HttpFailure(apiResult.code, apiResult.error)
                }
                is ApiResult.Failure.ApiFailure -> {
                    return apiResult.error.let(::ApiFailure)
                }
            }
            try {
                val days = tempDtoData!!.map { it.toDayDbo() }
                val lessons = days.mapNotNull { it.lessons }.flatten()
                dayDao.insertAndDeletePrevious(*days.toTypedArray())
                lessonDao.insertAndDeletePrevious(*lessons.toTypedArray())

                return days.let(::Success)
            } catch (e: Throwable) {
                return e.let(::DatabaseFailure)
            }
        }
        val days = daysWithLessonsDbo.map { it.dayDbo.apply { this.lessons = it.lessons } }
        return days.let(::Success)
    }

    suspend fun deleteSchedule() {
        dayDao.deleteAll()
    }
}