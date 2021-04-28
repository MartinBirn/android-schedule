package com.grsu.schedule_project.data.network.retrofit

import com.grsu.schedule_project.data.model.dto.ErrorWrapperDto
import com.grsu.schedule_project.data.model.dto.FacultiesDto
import com.grsu.schedule_project.data.network.GET_FACULTIES_PATH
import com.grsu.schedule_project.data.network.LANGUAGE_PATH_KEY
import com.slack.eithernet.ApiResult
import retrofit2.http.GET
import retrofit2.http.Query

interface FacultyService {

    @GET(GET_FACULTIES_PATH)
    suspend fun getFaculties(@Query(LANGUAGE_PATH_KEY) language: String? = null): ApiResult<FacultiesDto, ErrorWrapperDto>
}