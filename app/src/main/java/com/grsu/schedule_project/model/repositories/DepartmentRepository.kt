package com.grsu.schedule_project.model.repositories

import android.content.SharedPreferences
import com.grsu.schedule_project.core.APP_LANGUAGE_KEY
import com.grsu.schedule_project.core.preferences.util.string
import com.grsu.schedule_project.database.dao.DepartmentDao
import com.grsu.schedule_project.model.dto.DepartmentDto
import com.grsu.schedule_project.model.errorhandling.RepoResult
import com.grsu.schedule_project.model.errorhandling.RepoResult.Failure.*
import com.grsu.schedule_project.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.model.mappers.toDepartmentDbo
import com.grsu.schedule_project.network.retrofit.ApiService
import com.slack.eithernet.ApiResult

class DepartmentRepository(
    private val preferences: SharedPreferences,
    private val scheduleService: ApiService,
    private val departmentDao: DepartmentDao
) {

    private val appLanguage by preferences.string(APP_LANGUAGE_KEY)

    suspend fun getDepartments(): RepoResult<*, *> {
        val departmentDboList = departmentDao.getAll()
        if (departmentDboList.isNullOrEmpty()) {
            val tempDtoData: List<DepartmentDto>?
            when (val apiResult = scheduleService.getDepartments(language = appLanguage)) {
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
                val tempDboData =
                    tempDtoData!!.map { departmentDto -> departmentDto.toDepartmentDbo() }
                departmentDao.insertAndDeletePrevious(*tempDboData.toTypedArray())
                return tempDboData.let(::Success)
            } catch (e: Throwable) {
                return e.let(::DatabaseFailure)
            }
        }
        return departmentDboList.let(::Success)
    }

    suspend fun deleteDepartments() {
        departmentDao.deleteAll()
    }
}