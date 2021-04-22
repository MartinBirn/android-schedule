package com.grsu.schedule_project.model.repositories

import android.content.SharedPreferences
import com.grsu.schedule_project.core.APP_LANGUAGE_KEY
import com.grsu.schedule_project.core.preferences.util.string
import com.grsu.schedule_project.database.dao.GroupDao
import com.grsu.schedule_project.model.dto.GroupDto
import com.grsu.schedule_project.model.errorhandling.RepoResult
import com.grsu.schedule_project.model.errorhandling.RepoResult.Failure.*
import com.grsu.schedule_project.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.model.mappers.toGroupDbo
import com.grsu.schedule_project.network.retrofit.ApiService
import com.slack.eithernet.ApiResult

class GroupRepository(
    private val preferences: SharedPreferences,
    private val scheduleService: ApiService,
    private val groupDao: GroupDao
) {

    private val appLanguage by preferences.string(APP_LANGUAGE_KEY)

    suspend fun getGroups(
        departmentId: String,
        facultyId: String,
        course: String
    ): RepoResult<*, *> {
        val groupDtoList = groupDao.getAll()
        if (groupDtoList.isNullOrEmpty()) {
            val tempDtoData: List<GroupDto>?
            when (val apiResult = scheduleService.getGroups(
                departmentId = departmentId,
                facultyId = facultyId,
                course = course,
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
                val tempDboData = tempDtoData!!.map { groupDto -> groupDto.toGroupDbo() }
                groupDao.insertAndDeletePrevious(*tempDboData.toTypedArray())
                return tempDboData.let(::Success)
            } catch (e: Throwable) {
                return e.let(::DatabaseFailure)
            }
        }
        return groupDtoList.let(::Success)
    }

    suspend fun deleteGroups() {
        groupDao.deleteAll()
    }
}