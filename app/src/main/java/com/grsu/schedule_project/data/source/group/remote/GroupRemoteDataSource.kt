package com.grsu.schedule_project.data.source.group.remote

import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.data.model.dto.GroupDto
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Failure.*
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.data.network.retrofit.GroupService
import com.grsu.schedule_project.data.source.group.GroupDataSource
import com.slack.eithernet.ApiResult

class GroupRemoteDataSource(
    private val groupService: GroupService,
    private val utils: Utils
) : GroupDataSource {

    override suspend fun getGroups(
        departmentId: String,
        facultyId: String,
        course: String,
        language: String?
    ): RepoResult<List<GroupDto>, *> {
        return when (val apiResult = groupService.getGroups(
            departmentId = departmentId,
            facultyId = facultyId,
            course = course,
            language = language
        )) {
            is ApiResult.Success -> apiResult.response.items?.let(::Success)
                ?: utils.getStringById(R.string.error_null_body).let(::ApiFailure)
            is ApiResult.Failure.NetworkFailure -> apiResult.error.let(::NetworkFailure)
            is ApiResult.Failure.UnknownFailure -> apiResult.error.let(::UnknownFailure)
            is ApiResult.Failure.HttpFailure -> HttpFailure(apiResult.code, apiResult.error)
            is ApiResult.Failure.ApiFailure -> apiResult.error.let(::ApiFailure)
        }
    }
}