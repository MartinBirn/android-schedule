package com.grsu.schedule_project.data.source.group

import android.content.SharedPreferences
import com.grsu.schedule_project.common.APP_LANGUAGE_KEY
import com.grsu.schedule_project.common.preferences.util.string
import com.grsu.schedule_project.data.model.dto.GroupDto
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Failure.DatabaseFailure
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.data.model.mappers.toGroupDbo
import com.grsu.schedule_project.data.source.group.local.GroupLocalDataSource
import com.grsu.schedule_project.data.source.group.remote.GroupRemoteDataSource

class GroupRepository(
    private val remoteDataSource: GroupRemoteDataSource,
    private val localDataSource: GroupLocalDataSource,
    private val preferences: SharedPreferences
) {

    private val appLanguage by preferences.string(APP_LANGUAGE_KEY)

    suspend fun getGroups(
        departmentId: String,
        facultyId: String,
        course: String
    ): RepoResult<*, *> {
        val groupDboList = localDataSource.getGroups(
            departmentId = departmentId,
            facultyId = facultyId,
            course = course,
            language = appLanguage
        )
        if (groupDboList is Success && groupDboList.response.isEmpty()) {
            val tempGroupDtoList: List<GroupDto>?
            when (val apiResult = remoteDataSource.getGroups(
                departmentId = departmentId,
                facultyId = facultyId,
                course = course,
                language = appLanguage
            )) {
                is Success -> tempGroupDtoList = apiResult.response
                else -> return apiResult
            }
            return try {
                val tempGroupDboList = tempGroupDtoList.map { it.toGroupDbo() }
                localDataSource.insertAndDeletePrevious(*tempGroupDboList.toTypedArray())
                tempGroupDboList.let(::Success)
            } catch (e: Throwable) {
                e.let(::DatabaseFailure)
            }
        }
        return groupDboList
    }

    suspend fun deleteGroups() {
        localDataSource.deleteAll()
    }
}