package com.grsu.schedule_project.data.source.teacher.remote

import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.data.model.dbo.TeacherDbo
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Failure.*
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.data.model.mappers.toTeacherDbo
import com.grsu.schedule_project.data.network.retrofit.TeacherService
import com.grsu.schedule_project.data.source.teacher.TeacherDataSource
import com.slack.eithernet.ApiResult

class TeacherRemoteDataSource(
    private val teacherService: TeacherService,
    private val utils: Utils
) : TeacherDataSource {

    override suspend fun getTeachers(
        teacherId: String?,
        sort: String?,
        language: String?
    ): RepoResult<List<TeacherDbo>, *> {
        return when (val apiResult =
            teacherService.getTeachers(teacherId, sort, language)) {
            is ApiResult.Success -> apiResult.response.items?.map { teacherDto -> teacherDto.toTeacherDbo() }
                ?.let(::Success) ?: utils.getStringById(R.string.error_null_body).let(::ApiFailure)
            is ApiResult.Failure.NetworkFailure -> apiResult.error.let(::NetworkFailure)
            is ApiResult.Failure.UnknownFailure -> apiResult.error.let(::UnknownFailure)
            is ApiResult.Failure.HttpFailure -> HttpFailure(apiResult.code, apiResult.error)
            is ApiResult.Failure.ApiFailure -> apiResult.error.let(::ApiFailure)
        }
    }
}