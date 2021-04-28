package com.grsu.schedule_project.data.source.faculty.remote

import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.data.model.dto.FacultyDto
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Failure.*
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.data.network.retrofit.FacultyService
import com.grsu.schedule_project.data.source.faculty.FacultyDataSource
import com.slack.eithernet.ApiResult

class FacultyRemoteDataSource(
    private val facultyService: FacultyService,
    private val utils: Utils
) : FacultyDataSource {

    override suspend fun getFaculties(language: String?): RepoResult<List<FacultyDto>, *> {
        return when (val apiResult = facultyService.getFaculties(language = language)) {
            is ApiResult.Success -> apiResult.response.items?.let(::Success)
                ?: utils.getStringById(R.string.error_null_body).let(::ApiFailure)
            is ApiResult.Failure.NetworkFailure -> apiResult.error.let(::NetworkFailure)
            is ApiResult.Failure.UnknownFailure -> apiResult.error.let(::UnknownFailure)
            is ApiResult.Failure.HttpFailure -> HttpFailure(apiResult.code, apiResult.error)
            is ApiResult.Failure.ApiFailure -> apiResult.error.let(::ApiFailure)
        }
    }
}