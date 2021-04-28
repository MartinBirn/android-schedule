package com.grsu.schedule_project.data.source.department.remote

import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.data.model.dbo.DepartmentDbo
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Failure.*
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.data.model.mappers.toDepartmentDbo
import com.grsu.schedule_project.data.network.retrofit.DepartmentService
import com.grsu.schedule_project.data.source.department.DepartmentDataSource
import com.slack.eithernet.ApiResult

class DepartmentRemoteDataSource(
    private val departmentService: DepartmentService,
    private val utils: Utils
) : DepartmentDataSource {

    override suspend fun getDepartments(language: String?): RepoResult<List<DepartmentDbo>, *> {
        return when (val apiResult = departmentService.getDepartments(language)) {
            is ApiResult.Success -> apiResult.response.items?.map { it.toDepartmentDbo() }
                ?.let(::Success) ?: utils.getStringById(R.string.error_null_body).let(::ApiFailure)
            is ApiResult.Failure.NetworkFailure -> apiResult.error.let(::NetworkFailure)
            is ApiResult.Failure.UnknownFailure -> apiResult.error.let(::UnknownFailure)
            is ApiResult.Failure.HttpFailure -> HttpFailure(apiResult.code, apiResult.error)
            is ApiResult.Failure.ApiFailure -> apiResult.error.let(::ApiFailure)
        }
    }
}