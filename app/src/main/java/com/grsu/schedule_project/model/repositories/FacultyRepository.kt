package com.grsu.schedule_project.model.repositories

import android.content.SharedPreferences
import com.grsu.schedule_project.core.APP_LANGUAGE_KEY
import com.grsu.schedule_project.core.preferences.util.string
import com.grsu.schedule_project.database.dao.FacultyDao
import com.grsu.schedule_project.model.dto.FacultyDto
import com.grsu.schedule_project.model.errorhandling.RepoResult
import com.grsu.schedule_project.model.errorhandling.RepoResult.Failure.*
import com.grsu.schedule_project.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.model.mappers.toFacultyDbo
import com.grsu.schedule_project.network.retrofit.ApiService
import com.slack.eithernet.ApiResult

class FacultyRepository(
    private val preferences: SharedPreferences,
    private val scheduleService: ApiService,
    private val facultyDao: FacultyDao
) {

    private val appLanguage by preferences.string(APP_LANGUAGE_KEY)

    suspend fun getFaculties(): RepoResult<*, *> {
        val facultyDboList = facultyDao.getAll()
        if (facultyDboList.isNullOrEmpty()) {
            val tempDtoData: List<FacultyDto>?
            when (val apiResult = scheduleService.getFaculties(language = appLanguage)) {
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
                val tempDboData = tempDtoData!!.map { facultyDto -> facultyDto.toFacultyDbo() }
                facultyDao.insertAndDeletePrevious(*tempDboData.toTypedArray())
                return tempDboData.let(::Success)
            } catch (e: Throwable) {
                return e.let(::DatabaseFailure)
            }
        }
        return facultyDboList.let(::Success)
    }

    suspend fun deleteFaculties() {
        facultyDao.deleteAll()
    }
}