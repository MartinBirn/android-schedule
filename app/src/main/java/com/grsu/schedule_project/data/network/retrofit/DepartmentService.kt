package com.grsu.schedule_project.data.network.retrofit

import com.grsu.schedule_project.data.model.dto.DepartmentsDto
import com.grsu.schedule_project.data.model.dto.ErrorWrapperDto
import com.grsu.schedule_project.data.network.GET_DEPARTMENTS_PATH
import com.grsu.schedule_project.data.network.LANGUAGE_PATH_KEY
import com.slack.eithernet.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface DepartmentService {

    @GET(GET_DEPARTMENTS_PATH)
    suspend fun getDepartments(@Query(LANGUAGE_PATH_KEY) language: String? = null): ApiResult<DepartmentsDto, ErrorWrapperDto>
}