package com.grsu.schedule_project.data.network.retrofit

import com.grsu.schedule_project.data.model.dto.ErrorWrapperDto
import com.grsu.schedule_project.data.model.dto.ScheduleDto
import com.grsu.schedule_project.data.network.*
import com.slack.eithernet.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface GroupScheduleService {

    @GET(GET_GROUP_SCHEDULE_PATH)
    suspend fun getGroupSchedule(
        @Query(GROUP_ID_PATH_KEY) groupId: String,
        @Query(DATE_START_PATH_KEY) dateStart: String? = null,
        @Query(DATE_END_PATH_KEY) dateEnd: String? = null,
        @Query(LANGUAGE_PATH_KEY) language: String? = null
    ): ApiResult<ScheduleDto, ErrorWrapperDto>
}