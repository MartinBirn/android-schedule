package com.grsu.schedule_project.model.repositories

import android.content.SharedPreferences
import com.grsu.schedule_project.core.APP_LANGUAGE_KEY
import com.grsu.schedule_project.core.preferences.util.string
import com.grsu.schedule_project.database.dao.TeacherDao
import com.grsu.schedule_project.model.dto.TeacherDto
import com.grsu.schedule_project.model.errorhandling.RepoResult
import com.grsu.schedule_project.model.errorhandling.RepoResult.Failure.*
import com.grsu.schedule_project.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.model.mappers.toTeacherDbo
import com.grsu.schedule_project.network.retrofit.ApiService
import com.slack.eithernet.ApiResult


class TeacherRepository(
    private val preferences: SharedPreferences,
    private val scheduleService: ApiService,
    private val teacherDao: TeacherDao
) {

    private val appLanguage by preferences.string(APP_LANGUAGE_KEY)

    suspend fun getTeachers(teacherId: String? = null, sort: String? = null): RepoResult<*, *> {
        val teacherDboList = teacherDao.getAll()
        if (teacherDboList.isNullOrEmpty()) {
            val tempDtoData: List<TeacherDto>?
            when (val apiResult =
                scheduleService.getTeachers(
                    teacherId = teacherId,
                    sort = sort,
                    language = appLanguage
                )) {
                is ApiResult.Success -> {
                    tempDtoData = apiResult.response.items
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
                val tempDboData = tempDtoData!!.map { teacherDto -> teacherDto.toTeacherDbo() }
                    //delete records with negative id's
                    .filter { teacherDbo -> teacherDbo.id?.toInt()!! > 0 }

                teacherDao.insertAndDeletePrevious(*tempDboData.toTypedArray())
                return tempDtoData.let(::Success)
            } catch (e: Throwable) {
                return e.let(::DatabaseFailure)
            }
        }
        return teacherDboList.let(::Success)
    }

    suspend fun deleteTeachers() {
        teacherDao.deleteAll()
    }
}