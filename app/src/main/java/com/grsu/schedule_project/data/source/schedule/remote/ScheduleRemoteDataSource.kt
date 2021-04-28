package com.grsu.schedule_project.data.source.schedule.remote

import com.grsu.schedule_project.R
import com.grsu.schedule_project.common.utils.Utils
import com.grsu.schedule_project.data.model.dbo.DayDbo
import com.grsu.schedule_project.data.model.errorhandling.RepoResult
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Failure.*
import com.grsu.schedule_project.data.model.errorhandling.RepoResult.Success
import com.grsu.schedule_project.data.model.mappers.toDayDbo
import com.grsu.schedule_project.data.network.retrofit.GroupScheduleService
import com.grsu.schedule_project.data.source.schedule.ScheduleDataSource
import com.slack.eithernet.ApiResult

class ScheduleRemoteDataSource(
    private val groupScheduleService: GroupScheduleService,
    private val utils: Utils
) : ScheduleDataSource {

    override suspend fun getGroupSchedule(
        groupId: String,
        dateStart: String?,
        dateEnd: String?,
        language: String?
    ): RepoResult<List<DayDbo>, *> {
        return when (val apiResult =
            groupScheduleService.getGroupSchedule(groupId, dateStart, dateEnd, language)) {
            is ApiResult.Success -> apiResult.response.days?.map { it.toDayDbo() }
                ?.let(::Success) ?: utils.getStringById(R.string.error_null_body).let(::ApiFailure)
            is ApiResult.Failure.NetworkFailure -> apiResult.error.let(::NetworkFailure)
            is ApiResult.Failure.UnknownFailure -> apiResult.error.let(::UnknownFailure)
            is ApiResult.Failure.HttpFailure -> HttpFailure(apiResult.code, apiResult.error)
            is ApiResult.Failure.ApiFailure -> apiResult.error.let(::ApiFailure)
        }
    }
}